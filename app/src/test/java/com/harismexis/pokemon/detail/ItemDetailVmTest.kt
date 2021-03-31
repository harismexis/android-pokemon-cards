package com.harismexis.pokemon.detail

import com.harismexis.pokemon.extensions.toUiModel
import com.harismexis.pokemon.mockprovider.getMockLocalItemsFromFeedAllIdsValid

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
    fun retrievingLocalItem_localItemRetrievedAndLiveDataUpdated() {
        // given
        val mockItem = getMockLocalItemsFromFeedAllIdsValid()[0]
        val mockId = mockItem.id
        val mockUiModel = mockItem.toUiModel()
        mockLocalItemCall(mockId, mockItem)

        // when
        detailVm.retrieveItemById(mockId)

        // then
        verifyLocalItemRetrieved(mockId)
        verifyLiveDataChanged(mockUiModel)
    }

    @Test
    fun retrievingLocalItemThrowsError_nothingHappens() {
        // given
        val mockItem = getMockLocalItemsFromFeedAllIdsValid()[0]
        val mockId = mockItem.id
        mockLocalItemCallThrowsError()

        // when
        detailVm.retrieveItemById(mockId)

        // then
        verifyLocalItemRetrieved(mockId)
        verifyLiveDataNotChanged()
    }

}