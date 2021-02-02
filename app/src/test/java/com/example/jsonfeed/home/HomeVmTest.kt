package com.example.jsonfeed.home

import com.example.jsonfeed.extensions.toLocalItems
import com.example.jsonfeed.extensions.toUiModels
import com.example.jsonfeed.localdb.LocalItem
import com.example.jsonfeed.mockprovider.getMockFeedAllIdsValid
import com.example.jsonfeed.mockprovider.getMockLocalItemsFromFeedAllIdsValid
import com.example.jsonfeed.uimodel.UiModel
import com.nhaarman.mockitokotlin2.verifyZeroInteractions

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HomeVmTest : HomeVmTestSetup() {

    @Before
    fun setup() {
        doBeforeTest()
    }

    @Test
    fun internetOn_remoteCallDoneAndDataStoredAndLiveDataUpdated() {
        // given
        val mockFeed = getMockFeedAllIdsValid()
        val mockLocalItems = mockFeed.toLocalItems()
        val mockUiModels = mockLocalItems.toUiModels()

        mockInternetActive(true)
        mockFeedNetworkCall(mockFeed)

        // when
        homeVm.bind()

        // then
        verifyRemoteDataFetched(mockUiModels, mockLocalItems)
    }

    @Test
    fun internetOff_localItemsFetchedAndLiveDataUpdated() {
        // given
        val mockLocalItems = getMockLocalItemsFromFeedAllIdsValid()
        val mockUiModels = mockLocalItems.toUiModels()
        mockInternetActive(false)
        mockLocalItemsCall(mockLocalItems)

        // when
        homeVm.bind()

        // then
        verifyInternetChecked()
        verifyLocalItemsRetrieved()
        verifyLiveDataChanged(mockUiModels)
    }

    @Test
    fun onRefreshAndInternetOn_dataRefreshed() {
        // given
        val mockFeed = getMockFeedAllIdsValid()
        val mockLocalItems = mockFeed.toLocalItems()
        val mockUiModels = mockLocalItems.toUiModels()

        mockInternetActive(true)
        mockFeedNetworkCall(mockFeed)

        // when
        homeVm.refresh {}

        // then
        verifyRemoteDataFetched(mockUiModels, mockLocalItems)
    }

    @Test
    fun onRefreshAndInternetOff_nothingHappens() {
        // given
        mockInternetActive(false)

        // when
        homeVm.refresh {}

        // then
        verifyInternetChecked()
        verifyZeroInteractions(mockFeedRepo)
        verifyZeroInteractions(mockLocalRepo)
    }

    private fun verifyRemoteDataFetched(
        uiModels: List<UiModel>,
        localItems: List<LocalItem>
    ) {
        verifyInternetChecked()
        verifyRemoteDataRetrieved()
        verifyLiveDataChanged(uiModels)
        verifyDataStoredLocally(localItems)
    }

}