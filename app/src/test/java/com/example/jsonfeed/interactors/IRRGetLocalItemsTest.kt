package com.example.jsonfeed.interactors

import com.example.jsonfeed.data.LocalRepository
import com.example.jsonfeed.domain.Item
import com.example.jsonfeed.shared.UnitTestSetup
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class IRRGetLocalItemsTest : UnitTestSetup() {

    @Mock
    private lateinit var mockRepository: LocalRepository

    private lateinit var mockItems: List<Item>
    private lateinit var iRRGetLocalItems: IRRGetLocalItems

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        setupMocks()
        iRRGetLocalItems = IRRGetLocalItems(mockRepository)
    }

    private fun setupMocks() {
        mockItems = mockParser.getMockItemsFromFeedWithAllItemsValid()
        runBlocking {
            Mockito.`when`(mockRepository.getItems()).thenReturn(mockItems)
        }
    }

    @Test
    fun interactorInvoked_then_repositoryCallsExpectedMethodWithExpectedArgAndResult() =
        runBlocking {
            // when
            val items = iRRGetLocalItems.invoke()

            // then
            verify(mockRepository, times(1)).getItems()
            Assert.assertEquals(mockItems.size, items!!.size)
        }

}