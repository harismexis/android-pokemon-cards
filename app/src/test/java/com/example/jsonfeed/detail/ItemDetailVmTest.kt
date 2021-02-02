package com.example.jsonfeed.detail

import com.example.jsonfeed.extensions.toUiModel
import com.example.jsonfeed.mockprovider.getMockLocalItemsFromFeedAllIdsValid

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
        val mockLocalItem = getMockLocalItemsFromFeedAllIdsValid()[0]
        val mockId = mockLocalItem.id
        val mockUiModel = mockLocalItem.toUiModel()
        mockLocalItemCall(mockId, mockLocalItem)

        // when
        detailVm.retrieveItemById(mockId)

        // then
        verifyLocalItemRetrieved(mockId)
        verifyLiveDataChanged(mockUiModel)
    }

}