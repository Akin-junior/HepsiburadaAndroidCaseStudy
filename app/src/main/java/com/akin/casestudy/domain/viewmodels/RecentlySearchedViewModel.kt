package com.akin.casestudy.domain.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.akin.casestudy.data.CategoriesDatabase
import com.akin.casestudy.data.models.RecentlySearchedModel
import com.akin.casestudy.domain.repository.RecentlySearchedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecentlySearchedViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<RecentlySearchedModel>>
    private var recentlySearchedRepository: RecentlySearchedRepository

    init {
        val lastSearchedDao =
            CategoriesDatabase.getDatabase(application).lastSearchedDao()
        recentlySearchedRepository = RecentlySearchedRepository(lastSearchedDao)
        readAllData = recentlySearchedRepository.readAllData

    }

    fun addLastSearched(lastSearchedModel: RecentlySearchedModel) {
        viewModelScope.launch(Dispatchers.IO) {
            recentlySearchedRepository.addLastSearched(lastSearchedModel)
        }
    }
}