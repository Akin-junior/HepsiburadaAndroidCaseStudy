package com.akin.casestudy.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.akin.casestudy.data.models.LastSearchedModel


@Dao
interface LastSearchedDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLastSearched(lastSearchedObject: LastSearchedModel)

    @Query("SELECT * FROM lastSearched_table ORDER BY id ASC")
    fun readAllData() : LiveData<List<LastSearchedModel>>
}