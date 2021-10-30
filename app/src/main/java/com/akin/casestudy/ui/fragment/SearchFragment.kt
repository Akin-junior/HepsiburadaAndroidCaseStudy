package com.akin.casestudy.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akin.casestudy.data.models.LastSearchedModel
import com.akin.casestudy.data.models.mapper.PureCollectionModel
import com.akin.casestudy.databinding.FragmentSearchBinding
import com.akin.casestudy.domain.CategoriesViewModel
import com.akin.casestudy.domain.LastSearchedViewModel
import com.akin.casestudy.domain.SearchViewModel
import com.akin.casestudy.domain.factory.SearchViewModelFactory
import com.akin.casestudy.domain.repository.CollectionRepository
import com.akin.casestudy.ui.adapters.CategoriesAdapter
import com.akin.casestudy.ui.adapters.SearchAdapter
import com.akin.casestudy.ui.fragment.basefragment.BaseFragment
import com.akin.casestudy.util.Constants.Companion.LIMIT
import com.akin.casestudy.util.Constants.Companion.OFFSET
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    companion object {
        var staticQuery: String = ""
        var staticCategory: String = ""
    }

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var gridLayoutManager: GridLayoutManager
    private val list: ArrayList<PureCollectionModel> by lazy { arrayListOf() }
    private val categoriesViewModel: CategoriesViewModel by viewModels()
    private val lastSearchedViewModel: LastSearchedViewModel by viewModels()
    private val rcAdapter = SearchAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //insert data for Categories only one time
        val prefs = requireActivity()
            .getSharedPreferences("pref", Context.MODE_PRIVATE)
        val firstStart = prefs.getBoolean("firstStartHome", true)

        if (firstStart) {
            categoriesViewModel.insertDataForCategories()
            val prefs: SharedPreferences =
                requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putBoolean("firstStartHome", false)
            editor.apply()
        }
        categoriesRcInit()
        val repository = CollectionRepository()
        val viewModelFactory = SearchViewModelFactory(repository)
        searchViewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
        gridLayoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        initSearchRc()
        searchViewListener()
        lastSearchedViewModel
    }



    private fun setDataByApi(term: String) {
        searchViewModel.getCollections(term, "", 20, OFFSET)
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
        searchViewModel.getCollections("", "", 20, 0)
    }

    fun goneRecentlyViews() {

        binding.rcRecentlySearched.visibility = View.GONE
        binding.recentlySearchedText.visibility = View.GONE
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
        println("stateopen" + LIMIT)

        if (LIMIT < 200) {
            LIMIT += 20
            searchViewModel.getCollections(staticQuery, staticCategory, LIMIT, OFFSET)
        }
    }

    private fun initSearchRc() {
        binding.rcSearched.layoutManager = gridLayoutManager

        rcAdapter.clickListener = { data ->
            var datas=""
             data.genres?.forEach {
                 datas+=it
             }
            println(datas)
           val lastSearchedList = LastSearchedModel(0, data.artistName,data.collectionPrice,data.imageUrl,data.releaseDate,
               data.artistName,data.price,data.trackName,data.description,data.longDescription, data.previewUrl,
               datas,data.primaryGenreName,data.kind,data.formattedPrice)
            lastSearchedViewModel.addLastSearched(lastSearchedList)
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
                searchViewModel.getCollections(staticQuery, category, 20, OFFSET)
                staticCategory = category
            }
            binding.rcCategories.adapter = adapter

        })
    }

}