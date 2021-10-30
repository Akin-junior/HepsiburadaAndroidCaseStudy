package com.akin.casestudy.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.akin.casestudy.data.models.mapper.PureCollectionModel

@Entity(
    tableName = "lastSearched_table",
    indices = [Index(value = ["track_id"], unique = true)]
)
data class LastSearchedModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "track_id")
    var trackId:Int?,
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
