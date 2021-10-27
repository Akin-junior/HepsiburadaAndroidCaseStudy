package com.akin.casestudy.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akin.casestudy.domain.SearchViewModel
import com.akin.casestudy.repository.CollectionRepository

class SearchViewModelFactory(private val repository: CollectionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(repository) as T
    }
}