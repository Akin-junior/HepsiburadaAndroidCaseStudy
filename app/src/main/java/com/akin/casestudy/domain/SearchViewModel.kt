package com.akin.casestudy.domain

import android.app.Application
import androidx.lifecycle.*
import com.akin.casestudy.data.CategoriesDatabase
import com.akin.casestudy.data.models.CategoriesModel
import com.akin.casestudy.data.models.mapper.PureCollectionModel
import com.akin.casestudy.data.models.mapper.toDomain
import com.akin.casestudy.repository.CategoriesRepository
import com.akin.casestudy.repository.CollectionRepository
import com.akin.casestudy.util.DatabaseApplication
import kotlinx.coroutines.launch


class SearchViewModel(private val repository: CollectionRepository) : ViewModel() {
    private val _collectionList = MutableLiveData<List<PureCollectionModel>>()
    val collectionList: LiveData<List<PureCollectionModel>> = _collectionList

    private val _categoriesList = MutableLiveData<List<CategoriesModel>>()
    val categoriesList: LiveData<List<CategoriesModel>> = _categoriesList




    fun getCollections(artistName: String, entity: String) {
        viewModelScope.launch {
            val response = repository.getCustomCollection(artistName, entity)
            if (response.isSuccessful) {
                val data = response.body()
                _collectionList.value = data?.results?.map {
                    it.toDomain()
                }

            } else {
                println(response.code())
            }

        }

    }

    private fun initDatabase(){
        val categoriesDao = CategoriesDatabase.getDatabase(context = DatabaseApplication()).categoriesDao()
        val repository  = CategoriesRepository(categoriesDao)
        _categoriesList.value = repository.readAllData
    }

}