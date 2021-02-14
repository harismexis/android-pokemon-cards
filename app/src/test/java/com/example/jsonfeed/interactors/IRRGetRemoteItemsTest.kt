package com.example.jsonfeed.interactors

import com.example.jsonfeed.data.RemoteRepository
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
class IRRGetRemoteItemsTest : UnitTestSetup() {

    @Mock
    private lateinit var mockRepository: RemoteRepository

    private lateinit var mockItems: List<Item>
    private lateinit var subject: IRRGetRemoteItems

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        MockitoAnnotations.initMocks(this)
        setupMocks()
        subject = IRRGetRemoteItems(mockRepository)
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
            val items = subject.invoke()

            // then
            verify(mockRepository, times(1)).getItems()
            Assert.assertEquals(mockItems.size, items!!.size)
        }

}