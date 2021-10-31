package com.akin.casestudy.domain.repository

import androidx.lifecycle.LiveData
import com.akin.casestudy.data.RecentlySearchedDao
import com.akin.casestudy.data.models.RecentlySearchedModel


class RecentlySearchedRepository(private val recentlySearchedDao: RecentlySearchedDao) {
    val readAllData : LiveData<List<RecentlySearchedModel>> = recentlySearchedDao.readAllData()

   suspend fun addLastSearched(lastSearched: RecentlySearchedModel){
       recentlySearchedDao.addLastSearched(lastSearched)
    }
    suspend fun deleteSingle(lastSearched: RecentlySearchedModel){
        recentlySearchedDao.deleteRecentlySearched(lastSearched)
    }
    suspend fun deleteAll(){
        recentlySearchedDao.deleteAllData()
    }
}