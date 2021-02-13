package com.example.jsonfeed.framework.datasource.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.jsonfeed.instrumentedsetup.base.InstrumentedTestSetup
import com.example.jsonfeed.parser.BaseMockParser.Companion.EXPECTED_NUM_MODELS_ALL_IDS_VALID
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class PokemonLocalDaoTest: InstrumentedTestSetup() {

    private lateinit var dao: PokemonLocalDao
    private lateinit var database: PokemonDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, PokemonDatabase::class.java)
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
    fun savingItemsFromRemoteFeedWithAllItemsValid_then_expectedItemsRetrieved() = runBlocking {
        // given
        val entities = mockParser.getMockPokemonEntitiesFromFeedWithAllItemsValid()

        // when
        dao.insertItems(entities)
        val retrievedEntities = dao.getAllItems()

        // then
        Assert.assertNotNull(retrievedEntities)
        Assert.assertNotEquals(0, retrievedEntities!!.size)
        Assert.assertEquals(entities.size, retrievedEntities.size)
        Assert.assertEquals(entities, retrievedEntities)
        Assert.assertEquals(EXPECTED_NUM_MODELS_ALL_IDS_VALID, retrievedEntities.size)
    }

    @Test
    @Throws(Exception::class)
    fun savingItemsFromFeedWithAllIdsAbsent_noItemsRetrieved() = runBlocking {
        // given
        val entities = mockParser.getMockPokemonEntitiesFromFeedWithAllIdsAbsent()

        // when
        dao.insertItems(entities)
        val retrievedEntities = dao.getAllItems()

        // then
        Assert.assertNotNull(retrievedEntities)
        Assert.assertEquals(0, retrievedEntities!!.size)
        Assert.assertEquals(entities.size, retrievedEntities.size)
        Assert.assertEquals(entities, retrievedEntities)
    }

}
