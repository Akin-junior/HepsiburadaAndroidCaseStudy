package com.akin.casestudy.ui.fragment

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akin.casestudy.data.models.RecentlySearchedModel
import com.akin.casestudy.data.models.mapper.PureCollectionModel
import com.akin.casestudy.databinding.FragmentSearchBinding
import com.akin.casestudy.domain.factory.SearchViewModelFactory
import com.akin.casestudy.domain.repository.SearchRepository
import com.akin.casestudy.domain.viewmodels.CategoriesViewModel
import com.akin.casestudy.domain.viewmodels.RecentlySearchedViewModel
import com.akin.casestudy.domain.viewmodels.SearchViewModel
import com.akin.casestudy.ui.adapters.CategoriesAdapter
import com.akin.casestudy.ui.adapters.RecentlySearchedAdapter
import com.akin.casestudy.ui.adapters.SearchAdapter
import com.akin.casestudy.ui.fragment.basefragment.BaseFragment
import com.akin.casestudy.util.Statics.Companion.LIMIT
import com.akin.casestudy.util.Statics.Companion.staticCategory
import com.akin.casestudy.util.Statics.Companion.staticQuery
import com.akin.casestudy.util.makeBigger


class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var gridLayoutManager: GridLayoutManager
    private val list: ArrayList<PureCollectionModel> by lazy { arrayListOf() }
    private val recentlySearchedViewModel: RecentlySearchedViewModel by viewModels()
    private val rcAdapter = SearchAdapter()
    private val categoriesViewModel: CategoriesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        categoriesRcInit()
        val repository = SearchRepository()
        val viewModelFactory = SearchViewModelFactory(repository)
        searchViewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
        gridLayoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        initSearchRc()
        searchViewListener()
        readLastSearchedData()
        recentlySearchedViewModel
        deleteAllRecentlySearched()
    }


    private fun setDataByApi(term: String) {
        searchViewModel.getCollections(term, staticCategory, 20)
        searchViewModel.collectionList.observe(viewLifecycleOwner, { response ->
            list.clear()
            list.addAll(response)
            println(list.size)
            rcAdapter.loadCollectionsData(list)

        })
    }

    fun showRecentlyViews() {
        binding.rcRecentlySearched.visibility = View.VISIBLE
        binding.recentlySearchedText.visibility = View.VISIBLE
        binding.trashIcon.visibility = View.VISIBLE
        searchViewModel.getCollections("", "", 20)
    }

    fun goneRecentlyViews() {

        binding.rcRecentlySearched.visibility = View.GONE
        binding.recentlySearchedText.visibility = View.GONE
        binding.trashIcon.visibility = View.GONE
    }


    private fun checkRecyclerViewToEnd() {
        binding.rcSearched.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.rcSearched.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    loadMore()
                }
            }

        })

    }

    private fun searchViewListener() {
        binding.searchEditView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query?.length!! > 2) {
                    staticQuery = query
                    setDataByApi(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText?.isEmpty() == true) {
                    showRecentlyViews()
                } else {
                    if (newText?.length!! > 2) {
                        list.clear()
                        staticQuery = newText
                        setDataByApi(newText)
                    }
                    goneRecentlyViews()
                }
                return true
            }
        })
    }

    private fun loadMore() {
        if (LIMIT < 200) {
            LIMIT += 20
            searchViewModel.getCollections(staticQuery, staticCategory, LIMIT)
        }
    }

    private fun initSearchRc() {
        binding.rcSearched.layoutManager = gridLayoutManager
        rcAdapter.clickListener = { data ->
            addLastSearchedData(data = data)
            val action =
                SearchFragmentDirections.actionSearchFragmentToDetailFragment(data)
            findNavController().navigate(action)
        }
        binding.rcSearched.adapter = rcAdapter

        checkRecyclerViewToEnd()
    }

    private fun categoriesRcInit() {
        categoriesViewModel.readAllData.observe(viewLifecycleOwner, {
            val adapter = CategoriesAdapter(it) { category ->
                list.clear()
                println(category)
                println(staticQuery)
                searchViewModel.getCollections(staticQuery, category, 20)
                staticCategory = category
            }
            binding.rcCategories.adapter = adapter

        })
    }

    private fun addLastSearchedData(data: PureCollectionModel) {
        var genres_ = ""
        data.genres?.forEach {
            genres_ += "$it-"
        }

        val bigImageUrl = data.imageUrl?.makeBigger()
        println(bigImageUrl)
        val lastSearchedList = RecentlySearchedModel(
            0,
            data.trackId,
            data.artistName,
            data.collectionPrice,
            bigImageUrl,
            data.releaseDate,
            data.artistName,
            data.price,
            data.trackName,
            data.description,
            data.longDescription,
            data.previewUrl,
            genres_,
            data.primaryGenreName,
            data.kind,
            data.formattedPrice
        )

        recentlySearchedViewModel.addLastSearched(lastSearchedList)
    }

    private fun readLastSearchedData() {
        recentlySearchedViewModel.readAllData.observe(viewLifecycleOwner, { it ->
            binding.rcRecentlySearched.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            val lastSearchedAdapter = RecentlySearchedAdapter()
            lastSearchedAdapter.addDataForRc(it)
            binding.rcRecentlySearched.adapter = lastSearchedAdapter
            lastSearchedAdapter.clickListener = { currentData ->
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Are you sure?")
                builder.setMessage("Won't be able to recover this record!")
                builder.setPositiveButton("Yes") { dialog, which ->
                    recentlySearchedViewModel.deleteSingleRecentlySearched(currentData)
                    dialog.dismiss()
                }

                builder.setNegativeButton("No") { dialog, which ->
                    dialog.dismiss()
                }

                builder.show()


            }
        })
    }

    private fun deleteAllRecentlySearched() {
        binding.trashIcon.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Are you sure?")
            builder.setMessage("Won't be able to recover this record!")
            builder.setPositiveButton("Yes") { dialog, which ->
                recentlySearchedViewModel.deleteAllRecentlySearchedData()
                dialog.dismiss()
            }

            builder.setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }

            builder.show()
        }

    }


}