package com.akin.casestudy.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.akin.casestudy.data.models.mapper.PureCollectionModel

@Entity(tableName = "lastSearched_table")
data class LastSearchedModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var collectionName: String?,
    val collectionPrice: Double?,
    val imageUrl: String?,
    val releaseDate: String?,
    val artistName: String?,
    val price: Double?,
    val trackName: String?,
    val description: String?,
    val longDescription: String?,
    val previewUrl: String?,
    val genres: String?,
    val primaryGenreName: String?,
    val kind: String?,
    val formattedPrice: String?,
)
