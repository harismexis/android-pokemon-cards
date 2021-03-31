package com.harismexis.pokemon.datasource

import com.harismexis.pokemon.framework.datasource.db.PokemonLocalDao
import com.harismexis.pokemon.framework.datasource.db.PokemonLocalDataSource
import com.harismexis.pokemon.framework.extensions.toPokemonEntities
import com.harismexis.pokemon.framework.extensions.toPokemonEntity
import com.harismexis.pokemon.shared.UnitTestSetup
import com.harismexis.pokemon.utils.PokemonItemVerificator
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