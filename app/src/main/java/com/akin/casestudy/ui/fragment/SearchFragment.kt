package com.akin.casestudy.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.akin.casestudy.R
import com.akin.casestudy.databinding.FragmentSearchBinding
import com.akin.casestudy.domain.SearchViewModel
import com.akin.casestudy.factory.SearchViewModelFactory
import com.akin.casestudy.repository.CollectionRepository
import com.akin.casestudy.ui.adapters.CategoriesAdapter
import com.akin.casestudy.ui.adapters.SearchAdapter
import com.akin.casestudy.ui.fragment.basefragment.BaseFragment


class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    companion object{
        var staticQuery:String = ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var images: List<Int>
    private lateinit var names: List<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoriesRcInit()
        val repository = CollectionRepository()
        val viewModelFactory = SearchViewModelFactory(repository)
        searchViewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
        searchViewListener()

    }

    private fun categoriesRcInit() {
        images = listOf(
            R.drawable.movie_icon,
            R.drawable.musics_icon,
            R.drawable.app_store_icon,
            R.drawable.ic__055107_bookshelf_books_library_icon,

            )
        names = listOf(
            "Movie",
            "Music",
            "Apps",
            "Books",
        )

        val adapter = CategoriesAdapter(nameList = names, imageList = images){names->
            println(names)
            println(staticQuery)
            searchViewModel.getCollections(staticQuery,names)
        }
        binding.rcCategories.adapter = adapter

    }

    private fun searchViewListener() {

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query?.length!! > 2) {
                    staticQuery = query
                    setDataByApi(query)
                }


                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.isEmpty() == true) {
                    binding.rcRecentlySearched.visibility = View.VISIBLE
                    binding.recentlySearchedText.visibility = View.VISIBLE
                    binding.rcSearched.visibility = View.GONE

                } else {
                    if (newText?.length!! > 2) {
                        staticQuery = newText
                        setDataByApi(newText)
                    }
                    binding.rcSearched.visibility = View.VISIBLE
                    binding.rcRecentlySearched.visibility = View.GONE
                    binding.recentlySearchedText.visibility = View.GONE
                }
                return true
            }
        })
    }

    private fun setDataByApi(term: String) {
        val gridLayoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        binding.rcSearched.layoutManager = gridLayoutManager
        try {
            searchViewModel.getCollections(term, "")
        } catch (e1: Exception) {
            println(e1.toString())
        }
        searchViewModel.collectionList.observe(viewLifecycleOwner) { response ->
            val rcAdapter = SearchAdapter(response)
            binding.rcSearched.adapter = rcAdapter

        }
    }


}