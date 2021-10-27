package com.akin.casestudy.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories_table")
data class CategoriesModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val image: Int,
    val shownName: String
)
