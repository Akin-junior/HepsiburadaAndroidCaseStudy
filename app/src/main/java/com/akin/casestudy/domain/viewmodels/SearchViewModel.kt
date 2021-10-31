package com.akin.casestudy.domain.viewmodels

import androidx.lifecycle.*
import com.akin.casestudy.data.models.mapper.PureCollectionModel
import com.akin.casestudy.data.models.mapper.toDomain
import com.akin.casestudy.domain.repository.SearchRepository
import kotlinx.coroutines.launch


class SearchViewModel(private val repository: SearchRepository) : ViewModel() {
    private val _collectionList = MutableLiveData<List<PureCollectionModel>>()
    val collectionList: LiveData<List<PureCollectionModel>> = _collectionList

    fun getCollections(artistName: String, entity: String, limit: Int)  {

        viewModelScope.launch {
            val response = repository.getCustomCollection(artistName, entity, limit)
            println(response)
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