package com.example.jsonfeed.detail

import com.example.jsonfeed.extensions.toLocalItem
import com.example.jsonfeed.extensions.toUiModel
import com.example.jsonfeed.extensions.toUiModels
import com.example.jsonfeed.testutils.mockprovider.provideMockFeedValid
import com.example.jsonfeed.testutils.mockprovider.provideMockLocalItemsValid

import com.nhaarman.mockitokotlin2.verify

import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class ItemDetailVmTest : ItemDetailVmTestSetup() {

    @Before
    fun setup() {
        doBeforeTest()
    }

    @Test
    fun selectedIdIsSet_localItemIsRetrieved() {
        // given
        val mockLocalItem = provideMockLocalItemsValid()[0]
        val id = mockLocalItem.id
        val expectedUIModel = mockLocalItem.toUiModel()

        runBlocking {
            Mockito.`when`(mockLocalRepo.getItemById(id)).thenReturn(mockLocalItem)
        }

        // when
        detailVm.itemId = id
        detailVm.retrieveItemById()

        // then
        runBlocking {
            verify(mockLocalRepo, Mockito.times(1)).getItemById(id)
            verify(observer).onChanged(expectedUIModel)
        }
    }

}