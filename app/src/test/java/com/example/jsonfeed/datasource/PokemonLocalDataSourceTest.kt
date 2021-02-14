package com.example.jsonfeed.datasource

import com.example.jsonfeed.domain.Item
import com.example.jsonfeed.framework.datasource.db.PokemonEntity
import com.example.jsonfeed.framework.datasource.db.PokemonLocalDao
import com.example.jsonfeed.framework.datasource.db.PokemonLocalDataSource
import com.example.jsonfeed.framework.datasource.network.PokemonFeed
import com.example.jsonfeed.framework.datasource.network.PokemonRemoteDao
import com.example.jsonfeed.framework.datasource.network.PokemonRemoteDataSource
import com.example.jsonfeed.framework.extensions.toPokemonEntities
import com.example.jsonfeed.framework.extensions.toPokemonEntity
import com.example.jsonfeed.shared.UnitTestSetup
import com.example.jsonfeed.utils.PokemonItemVerificator
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(JUnit4::class)
class PokemonLocalDataSourceTest : UnitTestSetup() {

    @Mock
    private lateinit var mockDao: PokemonLocalDao

    private var verificator = PokemonItemVerificator()

    private lateinit var subject: PokemonLocalDataSource

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        subject = PokemonLocalDataSource(mockDao)
    }

    @Test
    fun dataSourceInsertsItems_then_daoInsertsExpectedEntities() {
        // when
        runBlocking {
            // given
            val mockItems = mockParser.getMockItemsFromFeedWithAllItemsValid()
            val mockEntities = mockItems.toPokemonEntities()

            // when
            subject.insert(mockItems)

            // then
            verify(mockDao, times(1)).insertItems(mockEntities)
        }
    }

    @Test
    fun dataSourceRequestsItem_then_daoRetrievesExpectedEntity() {
        // when
        runBlocking {
            // given
            val mockEntity = mockParser.getMockItemValid().toPokemonEntity()
            val mockItemId = mockEntity.id
            Mockito.`when`(mockDao.getItemById(mockItemId)).thenReturn(mockEntity)

            // when
            val entity = subject.getItem(mockItemId)

            // then
            verify(mockDao, times(1)).getItemById(mockItemId)
        }
    }

    @Test
    fun dataSourceRequestsItems_then_daoRetrievesExpectedEntities() {
        // when
        runBlocking {
            // given
            val mockEntities = mockParser.getMockPokemonEntitiesFromFeedWithAllItemsValid()
            Mockito.`when`(mockDao.getAllItems()).thenReturn(mockEntities)

            // when
            val entities = subject.getAll()

            // then
            verify(mockDao, times(1)).getAllItems()
        }
    }

}