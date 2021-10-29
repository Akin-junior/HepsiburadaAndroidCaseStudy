package com.akin.casestudy.util

import android.app.Application
import com.akin.casestudy.data.CategoriesDatabase
import com.akin.casestudy.domain.repository.CategoriesRepository

class DatabaseApplication: Application() {

    private val categoriesDatabase by lazy { CategoriesDatabase.getDatabase(this) }
    val repository by lazy { CategoriesRepository(categoriesDatabase.categoriesDao()) }

}