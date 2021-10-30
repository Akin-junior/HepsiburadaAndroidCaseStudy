package com.akin.casestudy.domain.repository

import androidx.lifecycle.LiveData
import com.akin.casestudy.data.LastSearchedDao
import com.akin.casestudy.data.models.LastSearchedModel


class LastSearchedRepository(private val lastSearchedDao: LastSearchedDao) {
    val readAllData : LiveData<List<LastSearchedModel>> = lastSearchedDao.readAllData()

   suspend fun addLastSearched(lastSearched: LastSearchedModel){
        lastSearchedDao.addLastSearched(lastSearched)

    }
}