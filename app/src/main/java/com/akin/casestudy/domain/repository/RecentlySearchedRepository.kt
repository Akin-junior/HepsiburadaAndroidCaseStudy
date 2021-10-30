package com.akin.casestudy.domain.repository

import androidx.lifecycle.LiveData
import com.akin.casestudy.data.RecentlySearchedDao
import com.akin.casestudy.data.models.RecentlySearchedModel


class RecentlySearchedRepository(private val lastSearchedDao: RecentlySearchedDao) {
    val readAllData : LiveData<List<RecentlySearchedModel>> = lastSearchedDao.readAllData()

   suspend fun addLastSearched(lastSearched: RecentlySearchedModel){
        lastSearchedDao.addLastSearched(lastSearched)

    }
}