package com.example.jsonfeed.framework.datasource.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.jsonfeed.instrumentedsetup.base.InstrumentedTestSetup
import com.example.jsonfeed.parser.BaseMockParser.Companion.EXPECTED_NUM_MODELS_ALL_IDS_VALID
import com.example.jsonfeed.parser.BaseMockParser.Companion.EXPECTED_NUM_MODELS_FOR_NO_DATA
import com.example.jsonfeed.parser.BaseMockParser.Companion.EXPECTED_NUM_MODELS_WHEN_TWO_IDS_ABSENT
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
        verifyActualAgainstExpected(retrievedEntities!!, entities, EXPECTED_NUM_MODELS_ALL_IDS_VALID)
    }

    @Test
    @Throws(Exception::class)
    fun savingItemsFromRemoteFeedWithSomeIdsAbsent_then_expectedItemsRetrieved() = runBlocking {
        // given
        val entities = mockParser.getMockPokemonEntitiesFromFeedWithSomeIdsAbsent()

        // when
        dao.insertItems(entities)
        val retrievedEntities = dao.getAllItems()

        // then
        verifyActualAgainstExpected(retrievedEntities!!, entities, EXPECTED_NUM_MODELS_WHEN_TWO_IDS_ABSENT)
    }


    @Test
    @Throws(Exception::class)
    fun savingItemsFromFeedWithAllIdsAbsent_then_noItemsRetrieved() = runBlocking {
        // given
        val entities = mockParser.getMockPokemonEntitiesFromFeedWithAllIdsAbsent()

        // when
        dao.insertItems(entities)
        val retrievedEntities = dao.getAllItems()

        // then
        verifyActualAgainstExpected(retrievedEntities!!, entities, EXPECTED_NUM_MODELS_FOR_NO_DATA)
    }

    private fun verifyActualAgainstExpected(
        actual: List<PokemonEntity?>,
        expected: List<PokemonEntity>,
        expectedNumberOfItems: Int
    ) {
        Assert.assertNotNull(actual)
        Assert.assertNotNull(expected)
        Assert.assertEquals(expected.size, actual.size)
        Assert.assertEquals(expected, actual)
        Assert.assertEquals(expectedNumberOfItems, actual.size)
        Assert.assertEquals(expectedNumberOfItems, expected.size)
    }

}
