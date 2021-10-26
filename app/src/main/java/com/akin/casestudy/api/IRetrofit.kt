package com.akin.casestudy.api

import com.akin.casestudy.data.models.CollectionModels
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofit {
    @GET("search")
    suspend fun getSpesificName(
        @Query("term") artistName: String,
        @Query("entity") entity: String,
//        @QueryMap options:Map<String,String>,
        @Query("limit") limit: Int=5,
    ): Response<CollectionModels>

}