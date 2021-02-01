package com.example.jsonfeed.detail

import com.example.jsonfeed.extensions.toUiModel
import com.example.jsonfeed.mockprovider.provideMockLocalItems
import com.nhaarman.mockitokotlin2.verify

import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ItemDetailVmTest : ItemDetailVmTestSetup() {

    @Before
    fun setup() {
        doBeforeTest()
    }

    @Test
    fun selectedIdIsSet_localItemIsRetrieved() {
        // given
        val mockLocalItem = provideMockLocalItems()[0]
        val localItemId = mockLocalItem.id
        val expectedUIModel = mockLocalItem.toUiModel()
        mockLocalItemCall(localItemId, mockLocalItem)

        // when
        detailVm.retrieveItemById(localItemId)

        // then
        verifyLocalItemRetrieved(localItemId)
        verifyLiveDataChanged(expectedUIModel)
    }

}