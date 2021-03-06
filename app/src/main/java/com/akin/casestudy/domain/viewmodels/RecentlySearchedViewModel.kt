package com.akin.casestudy.domain.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.akin.casestudy.data.AppDataBase
import com.akin.casestudy.data.models.RecentlySearchedModel
import com.akin.casestudy.domain.repository.RecentlySearchedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecentlySearchedViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<RecentlySearchedModel>>
    private var recentlySearchedRepository: RecentlySearchedRepository

    init {
        val lastSearchedDao =
            AppDataBase.getDatabase(application).lastSearchedDao()
        recentlySearchedRepository = RecentlySearchedRepository(lastSearchedDao)
        readAllData = recentlySearchedRepository.readAllData

    }

    fun addLastSearched(lastSearchedModel: RecentlySearchedModel) {
        viewModelScope.launch(Dispatchers.IO) {
            recentlySearchedRepository.addLastSearched(lastSearchedModel)
        }
    }

    fun deleteSingleRecentlySearched(lastSearchedModel: RecentlySearchedModel) {
        viewModelScope.launch(Dispatchers.IO) {
            recentlySearchedRepository.deleteSingle(lastSearchedModel)
        }
    }

    fun deleteAllRecentlySearchedData() {
        viewModelScope.launch(Dispatchers.IO) {
            recentlySearchedRepository.deleteAll()
        }
    }
}