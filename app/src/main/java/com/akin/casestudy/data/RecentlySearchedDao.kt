package com.akin.casestudy.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.akin.casestudy.data.models.RecentlySearchedModel


@Dao
interface RecentlySearchedDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLastSearched(lastSearchedObject: RecentlySearchedModel)

    @Query("SELECT * FROM lastSearched_table ORDER BY id DESC")
    fun readAllData() : LiveData<List<RecentlySearchedModel>>
}