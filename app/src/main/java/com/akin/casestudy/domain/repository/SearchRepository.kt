package com.akin.casestudy.domain.repository

import com.akin.casestudy.data.api.RetrofitInstance
import com.akin.casestudy.data.models.CollectionModels
import retrofit2.Response

class SearchRepository {

    suspend fun getCustomCollection(
        artistName: String,
        entity: String,
        limit: Int
    ): Response<CollectionModels> {
        return RetrofitInstance.api.getSpesificName(artistName, entity, limit)
    }
}