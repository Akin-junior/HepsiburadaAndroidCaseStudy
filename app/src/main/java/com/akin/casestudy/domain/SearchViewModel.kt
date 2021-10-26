package com.akin.casestudy.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akin.casestudy.data.models.mapper.PureCollectionModel
import com.akin.casestudy.data.models.mapper.toDomain
import com.akin.casestudy.repository.Repository
import kotlinx.coroutines.launch


class SearchViewModel(private val repository: Repository) : ViewModel() {
    private val _collectionList = MutableLiveData<List<PureCollectionModel>>()
    val collectionList: LiveData<List<PureCollectionModel>> = _collectionList

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
}