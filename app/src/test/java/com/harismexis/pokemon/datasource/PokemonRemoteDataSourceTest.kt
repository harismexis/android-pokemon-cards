package com.harismexis.pokemon.datasource

import com.harismexis.pokemon.framework.datasource.network.PokemonRemoteDao
import com.harismexis.pokemon.framework.datasource.network.PokemonRemoteDataSource
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
class PokemonRemoteDataSourceTest : UnitTestSetup() {

    @Mock
    private lateinit var mockDao: PokemonRemoteDao

    private var verificator = PokemonItemVerificator()

    private lateinit var subject: PokemonRemoteDataSource

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        subject = PokemonRemoteDataSource(mockDao)
    }

    @Test
    fun dateSourceRequestsItems_then_daoRequestsItems() {
        // when
        runBlocking {
            // given
            val mockFeed = mockParser.getMockFeedAllIdsValid()
            Mockito.`when`(mockDao.getPokemonCards()).thenReturn(mockFeed)

            // when
            val items = subject.getItems()

            // then
            verify(mockDao, times(1)).getPokemonCards()
            verificator.verifyItemsAgainstRemoteFeed(items, mockFeed)
        }
    }



}