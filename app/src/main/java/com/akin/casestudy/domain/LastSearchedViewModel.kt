package com.akin.casestudy.domain

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.akin.casestudy.data.CategoriesDatabase
import com.akin.casestudy.data.models.LastSearchedModel
import com.akin.casestudy.domain.repository.LastSearchedRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LastSearchedViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<LastSearchedModel>>
    private var lastSearchedRepository: LastSearchedRepository

    init {
        val lastSearchedDao =
            CategoriesDatabase.getDatabase(application).lastSearchedDao()
        lastSearchedRepository = LastSearchedRepository(lastSearchedDao)
        readAllData = lastSearchedRepository.readAllData

    }

    fun addLastSearched(lastSearchedModel: LastSearchedModel) {
        viewModelScope.launch(Dispatchers.IO) {
            lastSearchedRepository.addLastSearched(lastSearchedModel)
        }
    }
}