package com.example.jsonfeed.localdb

import android.content.Context

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4

import com.example.jsonfeed.base.BaseTestSetup.Companion.EXPECTED_NUM_MODELS_ALL_FEED_IDS_VALID

import com.example.jsonfeed.framework.extensions.toLocalItems
import com.example.jsonfeed.framework.datasource.db.RoomDao
import com.example.jsonfeed.framework.datasource.db.LocalDatabase
import com.example.jsonfeed.mockprovider.getMockFeedAllIdsAbsent
import com.example.jsonfeed.mockprovider.getMockFeedAllIdsValid

import kotlinx.coroutines.runBlocking

import org.junit.*
import org.junit.runner.RunWith

import java.io.IOException

@RunWith(AndroidJUnit4::class)
class LocalDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: RoomDao
    private lateinit var database: LocalDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, LocalDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.getLocalDao()
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
        val savedItems = getMockFeedAllIdsValid().toLocalItems()

        // when
        //dao.insertItems(savedItems)
        val retrievedItems = dao.getAllItems()

        // then
        Assert.assertNotNull(retrievedItems)
        Assert.assertNotEquals(0, retrievedItems!!.size)
        Assert.assertEquals(savedItems.size, retrievedItems.size)
        Assert.assertEquals(savedItems, retrievedItems)
        Assert.assertEquals(EXPECTED_NUM_MODELS_ALL_FEED_IDS_VALID, retrievedItems.size)
    }

    @Test
    @Throws(Exception::class)
    fun savingFeedItemsWithNoIds_noLocalItemsRetrieved() = runBlocking {
        // given
        val savedItems = getMockFeedAllIdsAbsent().toLocalItems()

        // when
        //dao.insertItems(savedItems)
        val retrievedItems = dao.getAllItems()

        // then
        Assert.assertNotNull(retrievedItems)
        Assert.assertEquals(0, retrievedItems!!.size)
        Assert.assertEquals(savedItems.size, retrievedItems.size)
        Assert.assertEquals(savedItems, retrievedItems)
    }


}
