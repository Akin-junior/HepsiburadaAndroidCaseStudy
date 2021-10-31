package com.akin.casestudy.data.models.mapper

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PureCollectionModel(
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
    val genres: List<String>?,
    val primaryGenreName: String?,
    val kind: String?,
    val formattedPrice: String?,
    val trackId: Int?,

    ) : Parcelable