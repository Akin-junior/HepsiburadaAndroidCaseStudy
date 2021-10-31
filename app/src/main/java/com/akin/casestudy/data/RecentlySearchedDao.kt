package com.akin.casestudy.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.akin.casestudy.data.models.RecentlySearchedModel


@Dao
interface RecentlySearchedDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLastSearched(lastSearchedObject: RecentlySearchedModel)

    @Delete
    suspend fun deleteRecentlySearched(recentlySearchedModel: RecentlySearchedModel)

    @Query("DELETE FROM lastSearched_table")
    suspend fun deleteAllData()

    @Query("SELECT * FROM lastSearched_table ORDER BY id DESC")
    fun readAllData(): LiveData<List<RecentlySearchedModel>>
}