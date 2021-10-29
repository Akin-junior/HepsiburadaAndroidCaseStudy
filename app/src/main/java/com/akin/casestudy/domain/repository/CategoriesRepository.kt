package com.akin.casestudy.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.akin.casestudy.data.CategoriesDao
import com.akin.casestudy.data.models.CategoriesModel

class CategoriesRepository (private val categoriesDao: CategoriesDao) {
    val readAllData: LiveData<List<CategoriesModel>> = categoriesDao.readAllData()

   suspend fun  addCategories(categories:CategoriesModel){
        categoriesDao.addCategories(categories)
    }
}