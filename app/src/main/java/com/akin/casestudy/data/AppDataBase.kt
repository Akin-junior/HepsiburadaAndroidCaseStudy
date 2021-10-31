package com.akin.casestudy.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.akin.casestudy.data.models.CategoriesModel
import com.akin.casestudy.data.models.RecentlySearchedModel

@Database(
    entities = [CategoriesModel::class, RecentlySearchedModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun categoriesDao(): CategoriesDao
    abstract fun lastSearchedDao(): RecentlySearchedDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Application): AppDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "categories_database"
                ).build()
                INSTANCE = instance

                return instance
            }
        }
    }

}