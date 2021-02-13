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
class IRRGetLocalItemTest : UnitTestSetup() {

    @Mock
    private lateinit var mockRepository: LocalRepository

    private lateinit var mockItem: Item
    private lateinit var mockItemId: String
    private lateinit var iRRGetLocalItem: IRRGetLocalItem

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        MockitoAnnotations.initMocks(this)
        setupMocks()
        iRRGetLocalItem = IRRGetLocalItem((mockRepository))
    }

    private fun setupMocks() {
        mockItem = mockParser.getMockItemValid()
        mockItemId = mockItem.id
        runBlocking {
            Mockito.`when`(mockRepository.getItem(mockItemId)).thenReturn(mockItem)
        }
    }

    @Test
    fun interactorInvoked_then_repositoryCallsExpectedMethodWithExpectedArgAndResult() =
        runBlocking {
            // when
            val item = iRRGetLocalItem.invoke(mockItemId)

            // then
            verify(mockRepository, times(1)).getItem(mockItemId)
            Assert.assertEquals(mockItem.id, item!!.id)
        }

}