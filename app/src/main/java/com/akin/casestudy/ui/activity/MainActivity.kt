package com.akin.casestudy.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.akin.casestudy.R
import com.akin.casestudy.domain.SearchViewModel
import com.akin.casestudy.factory.SearchViewModelFactory
import com.akin.casestudy.repository.Repository

class MainActivity : AppCompatActivity() {
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val test: TextView = findViewById(R.id.test)
        val repository = Repository()
        val viewModelFactory = SearchViewModelFactory(repository)

        searchViewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
        try {
            searchViewModel.getCollections("jack johnson", "movie")
        } catch (e1: Exception) {
            test.text = e1.toString()
        }

        searchViewModel.collectionList.observe(this, { response ->
            try {
                test.text = "Successful"
                println(response)

                Log.d("Main", response.size.toString())
                val list: MutableList<String> = mutableListOf()
                response.forEach {
                    list.add(it.releaseDate)
                }
                Log.d("Main:", list.toString())

            } catch (e: Exception) {
                test.text = e.toString()
            }

        })

    }
}