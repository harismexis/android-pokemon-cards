package com.harismexis.pokemon.home

import com.harismexis.pokemon.extensions.toLocalItems
import com.harismexis.pokemon.extensions.toUiModels
import com.harismexis.pokemon.localdb.LocalItem
import com.harismexis.pokemon.mockprovider.getMockFeedAllIdsValid
import com.harismexis.pokemon.mockprovider.getMockLocalItemsFromFeedAllIdsValid
import com.harismexis.pokemon.uimodel.UiModel
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
        mockRemoteFeedCall(mockFeed)

        // when
        homeVm.bind()

        // then
        verifyRemoteFeedCallDoneAndDataStoredAndLiveDataUpdated(mockUiModels, mockLocalItems)
    }

    @Test
    fun internetOff_localItemsFetchedAndLiveDataUpdated() {
        // given
        val mockLocalItems = getMockLocalItemsFromFeedAllIdsValid()
        val mockUiModels = mockLocalItems.toUiModels()
        mockInternetActive(false)
        mockLocalFeedCall(mockLocalItems)

        // when
        homeVm.bind()

        // then
        verifyInternetChecked()
        verifyLocalFeedCallDone()
        verifyRemoteFeedCallNotDone()
        verifyDataNotStoredLocally()
        verifyLiveDataChanged(mockUiModels)
    }

    @Test
    fun onRefreshAndInternetOn_dataRefreshed() {
        // given
        val mockFeed = getMockFeedAllIdsValid()
        val mockLocalItems = mockFeed.toLocalItems()
        val mockUiModels = mockLocalItems.toUiModels()

        mockInternetActive(true)
        mockRemoteFeedCall(mockFeed)

        // when
        homeVm.refresh {}

        // then
        verifyRemoteFeedCallDoneAndDataStoredAndLiveDataUpdated(mockUiModels, mockLocalItems)
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
        verifyLiveDataNotChanged()
    }

    @Test
    fun remoteFeedCallThrowsError_nothingHappens() {
        // given
        mockInternetActive(true)
        mockRemoteFeedCallThrowsError()

        // when
        homeVm.bind()

        // then
        verifyInternetChecked()
        verifyRemoteFeedCallDone()
        verifyLiveDataNotChanged()
        verifyZeroInteractions(mockLocalRepo)
    }

    @Test
    fun localFeedCallThrowsError_nothingHappens() {
        // given
        mockInternetActive(false)
        mockLocalFeedCallThrowsError()

        // when
        homeVm.bind()

        // then
        verifyInternetChecked()
        verifyLocalFeedCallDone()
        verifyLiveDataNotChanged()
        verifyZeroInteractions(mockFeedRepo)
    }

    private fun verifyRemoteFeedCallDoneAndDataStoredAndLiveDataUpdated(
        uiModels: List<UiModel>,
        localItems: List<LocalItem>
    ) {
        verifyInternetChecked()
        verifyRemoteFeedCallDone()
        verifyLocalFeedCallNotDone()
        verifyLiveDataChanged(uiModels)
        verifyDataStoredLocally(localItems)
    }

}