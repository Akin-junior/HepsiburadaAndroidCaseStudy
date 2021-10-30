package com.akin.casestudy.domain

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.akin.casestudy.R
import com.akin.casestudy.data.CategoriesDatabase
import com.akin.casestudy.data.models.CategoriesModel
import com.akin.casestudy.domain.repository.CategoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<CategoriesModel>>
    private var categoriesRepository: CategoriesRepository

    init {
        val categoriesDao = CategoriesDatabase.getDatabase(application).categoriesDao()
        categoriesRepository = CategoriesRepository(categoriesDao)
        readAllData = categoriesRepository.readAllData

    }

    private fun addCategories(categoriesModel: CategoriesModel) {
        viewModelScope.launch(Dispatchers.IO) {
            categoriesRepository.addCategories(categoriesModel)
        }
    }
    fun insertDataForCategories(){
        val categories = CategoriesModel(0, "movie", R.drawable.movie_icon, "Movie")
        val categories2 = CategoriesModel(0, "music", R.drawable.musics_icon, "Musics")
        val categories3 = CategoriesModel(0, "software", R.drawable.app_store_icon, "Apps")
        val categories4 = CategoriesModel(0, "ebook", R.drawable.ic__055107_bookshelf_books_library_icon, "Books")
        addCategories(categories)
        addCategories(categories2)
        addCategories(categories3)
        addCategories(categories4)
    }

}