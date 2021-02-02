package com.example.jsonfeed.home

import com.example.jsonfeed.extensions.toLocalItems
import com.example.jsonfeed.extensions.toUiModels
import com.example.jsonfeed.mockprovider.getMockFeedAllIdsValid
import com.example.jsonfeed.mockprovider.getMockLocalItemsFromFeedAllIdsValid

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
    fun internetIsOnAndAllFeedItemIdsValid_remoteCallIsDoneAndDataIsStoredLocallyAndLiveDataIsUpdated() {
        // given
        val mockFeed = getMockFeedAllIdsValid()
        val mockLocalItems = mockFeed.toLocalItems()
        val mockUiModels = mockLocalItems.toUiModels()

        mockInternetActive(true)
        mockFeedNetworkCall(mockFeed)

        // when
        homeVm.bind()

        // then
        verifyRemoteDataRetrieved()
        verifyLiveDataChanged(mockUiModels)
        verifyDataStoredLocally(mockLocalItems)
    }

    @Test
    fun internetIsOffAndLocalStorageHasItems_localItemsAreFetchedAndLiveDataIsUpdated() {
        // given
        val mockLocalItems = getMockLocalItemsFromFeedAllIdsValid()
        val mockUiModels = mockLocalItems.toUiModels()
        mockInternetActive(false)
        mockLocalItemsCall(mockLocalItems)

        // when
        homeVm.bind()

        // then
        verifyLocalItemsRetrieved()
        verifyLiveDataChanged(mockUiModels)
    }

    @Test
    fun onRefresh_dataRefreshedIfInternetActive() {
        // given
        val mockFeed = getMockFeedAllIdsValid()
        val mockLocalItems = mockFeed.toLocalItems()
        val mockUiModels = mockLocalItems.toUiModels()

        mockInternetActive(true)
        mockFeedNetworkCall(mockFeed)

        // when
        homeVm.refresh {}

        // then
        verifyRemoteDataRetrieved()
        verifyLiveDataChanged(mockUiModels)
        verifyDataStoredLocally(mockLocalItems)
    }

}