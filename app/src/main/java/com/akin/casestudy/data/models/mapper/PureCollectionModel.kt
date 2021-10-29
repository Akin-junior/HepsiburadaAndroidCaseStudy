package com.akin.casestudy.data.models.mapper

import android.os.Parcel
import android.os.Parcelable

data class PureCollectionModel(
    var collectionName: String?,
    val collectionPrice: Double?,
    val imageUrl: String?,
    val releaseDate: String?,
    val artistName: String?,
    val price: Double?,
    val trackName: String?,
    val description: String?,
    val shortDescription: String?,
    val previewUrl: String?,
    val genres: List<String>?,
    val primaryGenreName: String?,

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(collectionName)
        parcel.writeValue(collectionPrice)
        parcel.writeString(imageUrl)
        parcel.writeString(releaseDate)
        parcel.writeString(artistName)
        parcel.writeValue(price)
        parcel.writeString(trackName)
        parcel.writeString(description)
        parcel.writeString(shortDescription)
        parcel.writeString(previewUrl)
        parcel.writeStringList(genres)
        parcel.writeString(primaryGenreName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PureCollectionModel> {
        override fun createFromParcel(parcel: Parcel): PureCollectionModel {
            return PureCollectionModel(parcel)
        }

        override fun newArray(size: Int): Array<PureCollectionModel?> {
            return arrayOfNulls(size)
        }
    }
}



