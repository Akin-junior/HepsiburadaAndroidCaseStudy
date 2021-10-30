package com.akin.casestudy.data.models.mapper

import com.akin.casestudy.data.models.Result

fun Result.toDomain(): PureCollectionModel {

    return PureCollectionModel(
        collectionName,
        collectionPrice,
        artworkUrl100,
        releaseDate,
        artistName,
        price,
        trackName,
        description,
        longDescription,
        previewUrl,
        genres,
        primaryGenreName,
        kind,
        formattedPrice

    )
}