package com.akin.casestudy.data

import androidx.test.filters.SmallTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import  androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.akin.casestudy.data.models.RecentlySearchedModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test



@SmallTest
@ExperimentalCoroutinesApi
class RecentlySearchedDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: RecentlySearchedDao
    private lateinit var database: CategoriesDatabase

    @Before
    fun setup() {
        //gecici database olusturuyoruz test icin
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CategoriesDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.lastSearchedDao()
    }
    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insertLastSearchedTesting(): Unit = runBlockingTest{
        //paralel bir thread istemiyoruz bu yüzden runBlocking calistiriyoruz
        val exampleSearchedData = RecentlySearchedModel(1,1,"test",1.1,"test",
        "test","Test",1.1,"test","test","test","test","test",
        "test","test","test")
        dao.addLastSearched(exampleSearchedData)
        val list = dao.readAllData().getOrAwaitValueTest()
        assertThat(list).contains(exampleSearchedData)
    }
    @Test
    fun deleteLastSearchedTesting(): Unit = runBlocking{
        //paralel bir thread istemiyoruz bu yüzden runBlocking calistiriyoruz
        val exampleSearchedData = RecentlySearchedModel(1,1,"test",1.1,"test",
            "test","Test",1.1,"test","test","test","test","test",
            "test","test","test")
        dao.deleteRecentlySearched(exampleSearchedData)
        val list = dao.readAllData().getOrAwaitValueTest()
        assertThat(list).doesNotContain(exampleSearchedData)
    }



}

