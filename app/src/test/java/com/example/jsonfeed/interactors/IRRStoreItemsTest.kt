package com.example.jsonfeed.interactors

import com.example.jsonfeed.data.LocalRepository
import com.example.jsonfeed.domain.Item
import com.example.jsonfeed.shared.UnitTestSetup
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