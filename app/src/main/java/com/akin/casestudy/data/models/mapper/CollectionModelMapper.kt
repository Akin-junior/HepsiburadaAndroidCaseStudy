package com.akin.casestudy.data.models.mapper

import com.akin.casestudy.data.models.Result

fun Result.toDomain(): PureCollectionModel {

    return PureCollectionModel(
        collectionName = collectionName,
        collectionPrice = collectionPrice,
        imageUrl = artworkUrl100,
        releaseDate = releaseDate
    )
}