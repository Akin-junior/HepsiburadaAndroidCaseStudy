package com.akin.casestudy.domain

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.akin.casestudy.data.CategoriesDatabase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LastSearchedViewModel(application: Application) : AndroidViewModel(application) {
//
//    val readAllData: LiveData<List<LastSearchedModel>>
//    private var lastSearchedRepository: LastSearchedRepository
//
//    init {
//        val lastSearchedDao =
//            CategoriesDatabase.getLastSearchedDatabase(application).lastSearchedDao()
//        lastSearchedRepository = LastSearchedRepository(lastSearchedDao)
//        readAllData = lastSearchedRepository.readAllData
//
//    }
//
//    fun addLastSearched(pureCollectionModel: LastSearchedModel) {
//        viewModelScope.launch(Dispatchers.IO) {
//            lastSearchedRepository.addLastSearched(pureCollectionModel)
//        }
//    }
}