package com.harismexis.pokemon.home

import androidx.lifecycle.Observer
import com.harismexis.pokemon.framework.util.network.ConnectivityMonitor
import com.harismexis.pokemon.presentation.home.viewmodel.HomeVm
import com.harismexis.pokemon.shared.VmTestSetup
import com.harismexis.pokemon.domain.Item
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import kotlinx.coroutines.runBlocking
import org.mockito.Mock
import org.mockito.Mockito

abstract class HomeVmTestSetup : VmTestSetup() {

    @Mock
    protected lateinit var mockConnectivity: ConnectivityMonitor

    @Mock
    lateinit var mockObserver: Observer<List<Item>>

    private val mockItems = mockParser.getMockItemsFromFeedWithAllItemsValid()
    protected lateinit var homeVm: HomeVm

    override fun initialise() {
        super.initialise()
        initialiseMockInteractors()
    }

    override fun initialiseClassUnderTest() {
        homeVm = HomeVm(mockInteractors, mockConnectivity)
    }

    private fun initialiseMockInteractors() {
        Mockito.`when`(mockInteractors.iRRGetRemoteItems).thenReturn(mockIRRGetRemoteItems)
        Mockito.`when`(mockInteractors.iRRGetLocalItems).thenReturn(mockIRRGetLocalItems)
        Mockito.`when`(mockInteractors.iRRStoreItems).thenReturn(mockIRRStoreItems)
    }

    // Internet

    protected fun mockInternetOn() {
        mockInternetActive(true)
    }

    protected fun mockInternetOff() {
        mockInternetActive(false)
    }

    private fun mockInternetActive(active: Boolean) {
        Mockito.`when`(mockConnectivity.isOnline()).thenReturn(active)
    }

    protected fun verifyInternetChecked() {
        verify(mockConnectivity, Mockito.times(1)).isOnline()
    }

    // Remote Call

    protected fun mockRemoteCallReturnsAllItemsValid() {
        mockRemoteCall(mockItems)
    }

    private fun mockRemoteCall(items: List<Item>) {
        runBlocking {
            Mockito.`when`(mockIRRGetRemoteItems.invoke()).thenReturn(items)
        }
    }

    protected fun mockRemoteCallThrowsError() {
        runBlocking {
            Mockito.`when`(mockIRRGetRemoteItems.invoke())
                .thenThrow(IllegalStateException("Error"))
        }
    }

    protected fun verifyRemoteCallDone() {
        runBlocking {
            verify(mockIRRGetRemoteItems, Mockito.times(1)).invoke()
        }
    }

    protected fun verifyRemoteCallNotDone() {
        runBlocking {
            verify(mockIRRGetRemoteItems, Mockito.never()).invoke()
        }
    }

    // Local Call

    protected fun mockLocalCallReturnsAllItemsValid() {
        mockLocalCall(mockItems)
    }

    private fun mockLocalCall(items: List<Item>) {
        runBlocking {
            Mockito.`when`(mockIRRGetLocalItems.invoke()).thenReturn(items)
        }
    }

    protected fun mockLocalCallThrowsError() {
        runBlocking {
            Mockito.`when`(mockIRRGetLocalItems.invoke())
                .thenThrow(IllegalStateException("Error"))
        }
    }

    protected fun verifyLocalCallDone() {
        runBlocking {
            verify(mockIRRGetLocalItems, Mockito.times(1)).invoke()
        }
    }

    protected fun verifyLocalCallNotDone() {
        runBlocking {
            verify(mockIRRGetLocalItems, Mockito.never()).invoke()
        }
    }

    // LiveData

    protected fun initialiseLiveData() {
        homeVm.models.observeForever(mockObserver)
    }

    protected fun verifyLiveDataChangedAsExpected() {
        verifyLiveDataChanged(mockItems)
    }

    private fun verifyLiveDataChanged(items: List<Item>) {
        verify(mockObserver).onChanged(items)
    }

    protected fun verifyLiveDataNotChanged() {
        verifyZeroInteractions(mockObserver)
    }

    // Store Data

    protected fun verifyDataStored() {
        verifyDataStored(mockItems)
    }

    private fun verifyDataStored(items: List<Item>) {
        runBlocking {
            verify(mockIRRStoreItems, Mockito.times(1)).invoke(items)
        }
    }

    protected fun verifyDataNotStored() {
        runBlocking {
            verify(mockIRRStoreItems, Mockito.never()).invoke(any())
        }
    }

}