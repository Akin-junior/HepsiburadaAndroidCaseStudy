package com.akin.casestudy.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.akin.casestudy.data.models.CategoriesModel

@Dao
interface CategoriesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun addCategories(categories:CategoriesModel)

    @Query("SELECT * FROM categories_table ORDER BY id ASC")
   fun readAllData() : LiveData< List<CategoriesModel>>
}