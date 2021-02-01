package com.example.jsonfeed.localdb

import android.content.Context

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4

import com.example.jsonfeed.extensions.toLocalItems
import com.example.jsonfeed.extensions.toUiModels
import com.example.jsonfeed.mockprovider.provideMockFeedValid

import kotlinx.coroutines.runBlocking

import org.junit.*
import org.junit.runner.RunWith

import java.io.IOException

@RunWith(AndroidJUnit4::class)
class LocalDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: LocalDao
    private lateinit var database: LocalDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, LocalDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.localDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun savingItems_correctItemsAreRetrieved() = runBlocking {
        // given
        val savedItems = provideMockFeedValid().toLocalItems()

        // when
        dao.insertItems(savedItems)
        val retrievedItems = dao.getAllItems()

        // then
        Assert.assertNotNull(retrievedItems)
        Assert.assertNotEquals(0, retrievedItems!!.size)
        Assert.assertEquals(savedItems.size, retrievedItems.size)
        Assert.assertEquals(savedItems.size, retrievedItems.size)
        Assert.assertEquals(savedItems, retrievedItems)
    }


}
