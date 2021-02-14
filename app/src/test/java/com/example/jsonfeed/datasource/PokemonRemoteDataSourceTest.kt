package com.example.jsonfeed.datasource

import com.example.jsonfeed.framework.datasource.network.PokemonFeed
import com.example.jsonfeed.framework.datasource.network.PokemonRemoteDao
import com.example.jsonfeed.framework.datasource.network.PokemonRemoteDataSource
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