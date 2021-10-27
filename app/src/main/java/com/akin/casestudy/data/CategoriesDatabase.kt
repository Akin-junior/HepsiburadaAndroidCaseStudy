package com.akin.casestudy.data

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.akin.casestudy.data.models.CategoriesModel
import com.akin.casestudy.util.DatabaseApplication
import kotlinx.coroutines.CoroutineScope

@Database(entities = [CategoriesModel::class], version = 1, exportSchema = false)
abstract class CategoriesDatabase : RoomDatabase() {
    abstract fun categoriesDao(): CategoriesDao

    companion object {
        @Volatile
        private var INSTANCE: CategoriesDatabase? = null

        fun getDatabase(context: DatabaseApplication): CategoriesDatabase {
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