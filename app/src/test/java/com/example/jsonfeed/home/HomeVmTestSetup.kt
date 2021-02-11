package com.example.jsonfeed.home

import androidx.lifecycle.Observer
import com.example.jsonfeed.domain.Item
import com.example.jsonfeed.framework.util.network.ConnectivityMonitor
import com.example.jsonfeed.presentation.home.viewmodel.HomeVm
import com.example.jsonfeed.shared.UnitTestSetup
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import kotlinx.coroutines.runBlocking
import org.mockito.Mock
import org.mockito.Mockito

abstract class HomeVmTestSetup : UnitTestSetup() {

    @Mock
    protected lateinit var mockConnectivity: ConnectivityMonitor
    @Mock
    lateinit var mockObserver: Observer<List<Item>>

    private val mockValidItems = mockParser.getMockItemsFromFeedWithAllItemsValid()
    protected lateinit var homeVm: HomeVm

    override fun initialiseClassUnderTest() {
        homeVm = HomeVm(mockInteractors, mockConnectivity)
        homeVm.models.observeForever(mockObserver)
    }

    override fun initialiseMockInteractors() {
        Mockito.`when`(mockInteractors.getRemoteItems).thenReturn(mockInteractorGetRemoteItems)
        Mockito.`when`(mockInteractors.getLocalItems).thenReturn(mockInteractorGetLocalItems)
        Mockito.`when`(mockInteractors.storeItems).thenReturn(mockInteractorStoreItems)
        val mockValidItems = mockParser.getMockItemsFromFeedWithAllItemsValid()
        runBlocking {
            Mockito.`when`(mockInteractorGetLocalItems.invoke()).thenReturn(mockValidItems)
            Mockito.`when`(mockInteractorGetRemoteItems.invoke()).thenReturn(mockValidItems)
        }
    }

    // ----------------------------------------------------

    protected fun mockInternetOn() {
        mockInternetActive(true)
    }

    protected fun mockInternetOff() {
        mockInternetActive(false)
    }

    protected fun mockInternetActive(active: Boolean) {
        Mockito.`when`(mockConnectivity.isOnline()).thenReturn(active)
    }

    protected fun verifyInternetChecked() {
        verify(mockConnectivity, Mockito.times(1)).isOnline()
    }

    // ----------------------------------------------------

    protected fun mockRemoteCallReturnsAllItemsValid() {
        mockRemoteCall(mockValidItems)
    }

    private fun mockRemoteCall(
        items: List<Item>
    ) {
        runBlocking {
            Mockito.`when`(mockInteractorGetRemoteItems.invoke()).thenReturn(items)
        }
    }

    protected fun verifyRemoteCallDone() {
        runBlocking {
            verify(mockInteractorGetRemoteItems,
                Mockito.times(1)).invoke()
        }
    }

    protected fun verifyRemoteCallNotDone() {
        runBlocking {
            // verify(mockFeedRepo, Mockito.never()).getJsonFeed()
            verify(mockInteractorGetRemoteItems, Mockito.never()).invoke()
        }
    }

    // ----------------------------------------------------

    protected fun mockRemoteCallThrowsError() {
        runBlocking {
            Mockito.`when`(mockInteractorGetRemoteItems.invoke())
                .thenThrow(IllegalStateException("Error"))
        }
    }

    protected fun mockLocalCallReturnsAllItemsValid() {
        mockLocalCall(mockValidItems)
    }

    private fun mockLocalCall(
        items: List<Item>
    ) {
        runBlocking {
            Mockito.`when`(mockInteractorGetLocalItems.invoke()).thenReturn(items)
        }
    }

    protected fun mockLocalCallThrowsError() {
        runBlocking {
            Mockito.`when`(mockInteractorGetLocalItems.invoke())
                .thenThrow(IllegalStateException("Error"))
        }
    }

    protected fun verifyLocalCallDone() {
        runBlocking {
            verify(mockInteractorGetLocalItems,
                Mockito.times(1)).invoke()
        }
    }

    protected fun verifyLocalCallNotDone() {
        runBlocking {
            verify(mockInteractorGetLocalItems, Mockito.never()).invoke()
        }
    }

    // ----------------------------------------------------

    protected fun verifyLiveDataChangedAsExpected() {
        verifyLiveDataChanged(mockValidItems)
    }

    protected fun verifyLiveDataChanged(
        items: List<Item>
    ) {
        verify(mockObserver).onChanged(items)
    }

    protected fun verifyLiveDataNotChanged() {
        verifyZeroInteractions(mockObserver)
    }

    // ----------------------------------------------------

    protected fun verifyDataStored(
    ) {
        verifyDataStored(mockValidItems)
    }

    private fun verifyDataStored(
        items: List<Item>
    ) {
        runBlocking {
            verify(mockInteractorStoreItems,
                Mockito.times(1)).invoke(items)
        }
    }

    protected fun verifyDataNotStored() {
        runBlocking {
            verify(mockInteractorStoreItems, Mockito.never()).invoke(any())
        }
    }

    // ----------------------------------------------------

}