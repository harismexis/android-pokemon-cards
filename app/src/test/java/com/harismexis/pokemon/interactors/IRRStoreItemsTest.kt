package com.harismexis.pokemon.interactors

import com.harismexis.pokemon.shared.UnitTestSetup
import com.harismexis.pokemon.data.LocalRepository
import com.harismexis.pokemon.domain.Item
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock

@RunWith(JUnit4::class)
class IRRStoreItemsTest : UnitTestSetup() {

    @Mock
    private lateinit var mockRepository: LocalRepository

    private lateinit var mockItems: List<Item>
    private lateinit var subject: IRRStoreItems

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        setupMocks()
        subject = IRRStoreItems(mockRepository)
    }

    private fun setupMocks() {
        mockItems = mockParser.getMockItemsFromFeedWithAllItemsValid()
    }

    @Test
    fun interactorInvoked_then_repositoryCallsExpectedMethodWithExpectedArgAndResult() =
        runBlocking {
            // when
            subject.invoke(mockItems)

            // then
            verify(mockRepository, times(1)).insertItems(mockItems)
        }

}