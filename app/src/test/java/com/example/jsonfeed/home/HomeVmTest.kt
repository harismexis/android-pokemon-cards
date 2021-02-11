package com.example.jsonfeed.home

import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HomeVmTest : HomeVmTestSetup() {

    @Before
    fun doBeforeEachTestCase() {
        initialise()
    }

    @Test
    fun internetOn_when_viewModelBinds_then_remoteCallDone_dataStored_liveDataUpdated() {
        // given
        mockInternetOn()
        mockRemoteCallReturnsAllItemsValid()

        // when
        homeVm.bind()

        // then
        verify_remoteFeedCallDone_dataStored_liveDataUpdated()
    }

    @Test
    fun internetOff_when_viewModelBinds_then_localItemsFetched_liveDataUpdated() {
        // given
        mockInternetOff()
        mockLocalCallReturnsAllItemsValid()

        // when
        homeVm.bind()

        // then
        verifyInternetChecked()
        verifyRemoteCallNotDone()
        verifyLocalCallDone()
        verifyDataNotStored()
        verifyLiveDataChangedAsExpected()
    }

    @Test
    fun internetOn_when_refresh_then_dataRefreshed() {
        // given
        mockInternetOn()
        mockRemoteCallReturnsAllItemsValid()

        // when
        homeVm.refresh {}

        // then
        verify_remoteFeedCallDone_dataStored_liveDataUpdated()
    }

    @Test
    fun internetOff_when_refresh_then_nothingHappens() {
        // given
        mockInternetOff()

        // when
        homeVm.refresh {}

        // then
        verifyInternetChecked()
        verifyZeroInteractions(mockInteractorGetRemoteItems)
        verifyZeroInteractions(mockInteractorGetLocalItems)
        verifyLiveDataNotChanged()
    }

    @Test
    fun remoteFeedCallThrowsError_nothingHappens() {
        // given
        mockInternetOn()
        mockRemoteCallThrowsError()

        // when
        homeVm.bind()

        // then
        verifyInternetChecked()
        verifyRemoteCallDone()
        verifyLiveDataNotChanged()
        verifyLiveDataNotChanged()
    }

    @Test
    fun localFeedCallThrowsError_nothingHappens() {
        // given
        mockInternetOff()
        mockLocalCallThrowsError()

        // when
        homeVm.bind()

        // then
        verifyInternetChecked()
        verifyRemoteCallNotDone()
        verifyLocalCallDone()
        verifyLiveDataNotChanged()
        verifyLiveDataNotChanged()
    }

    private fun verify_remoteFeedCallDone_dataStored_liveDataUpdated() {
        verifyInternetChecked()
        verifyRemoteCallDone()
        verifyLocalCallNotDone()
        verifyLiveDataChangedAsExpected()
        verifyDataStored()
    }

}