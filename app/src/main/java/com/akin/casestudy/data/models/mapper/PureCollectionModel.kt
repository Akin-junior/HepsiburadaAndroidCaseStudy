package com.akin.casestudy.data.models.mapper

import android.os.Parcel
import android.os.Parcelable

data class PureCollectionModel(
    var collectionName: String?,
    val collectionPrice: Double,
    val imageUrl: String,
    val releaseDate: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(collectionName)
        parcel.writeDouble(collectionPrice)
        parcel.writeString(imageUrl)
        parcel.writeString(releaseDate)
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
