package com.akin.casestudy.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.akin.casestudy.data.models.CategoriesModel
import com.akin.casestudy.data.models.RecentlySearchedModel

@Database(entities = [CategoriesModel::class,RecentlySearchedModel::class], version = 1, exportSchema = false)
abstract class CategoriesDatabase : RoomDatabase() {
    abstract fun categoriesDao(): CategoriesDao
    abstract fun lastSearchedDao(): RecentlySearchedDao

    companion object {
        @Volatile
        private var INSTANCE: CategoriesDatabase? = null

        fun getDatabase(context: Application): CategoriesDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CategoriesDatabase::class.java,
                    "categories_database"
                ).build()
                INSTANCE = instance

                return instance
            }
        }
    }

}