package com.akin.casestudy.repository

import com.akin.casestudy.api.RetrofitInstance
import com.akin.casestudy.data.models.CollectionModels
import com.akin.casestudy.data.models.mapper.PureCollectionModel
import retrofit2.Response

class Repository {

   suspend fun getCustomCollection(artistName: String, entity:String) : Response<CollectionModels> {
        return RetrofitInstance.api.getSpesificName(artistName,entity)
    }
}