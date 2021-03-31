package com.harismexis.pokemon.interactors

import com.harismexis.pokemon.shared.UnitTestSetup
import com.harismexis.pokemon.data.LocalRepository
import com.harismexis.pokemon.domain.Item
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(JUnit4::class)
class IRRGetLocalItemTest : UnitTestSetup() {

    @Mock
    private lateinit var mockRepository: LocalRepository

    private lateinit var mockItem: Item
    private lateinit var mockItemId: String
    private lateinit var subject: IRRGetLocalItem

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        setupMocks()
        subject = IRRGetLocalItem(mockRepository)
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
            val item = subject.invoke(mockItemId)

            // then
            verify(mockRepository, times(1)).getItem(mockItemId)
            Assert.assertEquals(mockItem.id, item!!.id)
        }

}