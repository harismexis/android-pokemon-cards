package com.example.jsonfeed.home

import com.example.jsonfeed.extensions.toLocalItems
import com.example.jsonfeed.extensions.toUiModels
import com.example.jsonfeed.mockprovider.provideMockFeedValid
import com.example.jsonfeed.mockprovider.provideMockLocalItems

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
    fun internetIsOnAndFeedIsValid_remoteCallIsDoneAndDataIsStoredLocallyAndLiveDataIsUpdated() {
        // given
        val mockFeedValid = provideMockFeedValid()
        val expectedLocalItems = mockFeedValid.toLocalItems()
        val expectedUiModels = expectedLocalItems.toUiModels()

        mockInternetActive(true)
        mockFeedNetworkCall(mockFeedValid)

        // when
        homeVm.bind()

        // then
        verifyRemoteDataRetrieved()
        verifyLiveDataChanged(expectedUiModels)
        verifyDataStoredLocally(expectedLocalItems)
    }

    @Test
    fun internetIsOffAndLocalStorageHasItems_localItemsAreFetchedAndLiveDataIsUpdated() {
        // given
        val mockLocalItems = provideMockLocalItems()
        val expectedUiModels = mockLocalItems.toUiModels()
        mockInternetActive(false)
        mockLocalItemsCall(mockLocalItems)

        // when
        homeVm.bind()

        // then
        verifyLocalItemsRetrieved()
        verifyLiveDataChanged(expectedUiModels)
    }

    @Test
    fun onRefresh_dataRefreshedIfInternetActive() {
        // given
        val mockFeedValid = provideMockFeedValid()
        val expectedLocalItems = mockFeedValid.toLocalItems()
        val expectedUiModels = expectedLocalItems.toUiModels()

        mockInternetActive(true)
        mockFeedNetworkCall(mockFeedValid)

        // when
        homeVm.refresh {}

        // then
        verifyRemoteDataRetrieved()
        verifyLiveDataChanged(expectedUiModels)
        verifyDataStoredLocally(expectedLocalItems)
    }

}