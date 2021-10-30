package com.akin.casestudy.data

import android.app.Application
import androidx.room.*
import com.akin.casestudy.data.models.CategoriesModel
import com.akin.casestudy.data.models.LastSearchedModel

@Database(entities = [CategoriesModel::class,LastSearchedModel::class], version = 1, exportSchema = false)
abstract class CategoriesDatabase : RoomDatabase() {
    abstract fun categoriesDao(): CategoriesDao
    abstract fun lastSearchedDao(): LastSearchedDao

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
//        fun getLastSearchedDatabase(context: Application): CategoriesDatabase {
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    CategoriesDatabase::class.java,
//                    "lastSearched_database"
//                ).build()
//                INSTANCE = instance
//
//                return instance
//            }
//        }
    }

}