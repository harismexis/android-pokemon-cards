package com.example.jsonfeed.home

import androidx.lifecycle.Observer

import com.example.jsonfeed.datamodel.Feed
import com.example.jsonfeed.repository.FeedRepository
import com.example.jsonfeed.home.viewmodel.HomeVm
import com.example.jsonfeed.localdb.LocalItem
import com.example.jsonfeed.shared.ViewModelBaseTestSetup
import com.example.jsonfeed.uimodel.UiModel
import com.example.jsonfeed.util.network.ConnectivityMonitor

import com.nhaarman.mockitokotlin2.verify

import kotlinx.coroutines.runBlocking

import org.mockito.Mock
import org.mockito.Mockito

abstract class HomeVmTestSetup : ViewModelBaseTestSetup() {

    @Mock
    protected lateinit var mockFeedRepo: FeedRepository

    @Mock
    protected lateinit var mockConnectivity: ConnectivityMonitor

    @Mock
    lateinit var observer: Observer<List<UiModel>>

    protected lateinit var homeVm: HomeVm

    override fun initialiseClassUnderTest() {
        homeVm = HomeVm(mockFeedRepo, mockLocalRepo, mockConnectivity)
        homeVm.models.observeForever(observer)
    }

    protected fun mockInternetActive(active: Boolean) {
        Mockito.`when`(mockConnectivity.isOnline()).thenReturn(active)
    }

    protected fun mockFeedNetworkCall(feed: Feed) {
        runBlocking {
            Mockito.`when`(mockFeedRepo.getJsonFeed()).thenReturn(feed)
        }
    }

    protected fun mockLocalItemsCall(localItems: List<LocalItem>) {
        runBlocking {
            Mockito.`when`(mockLocalRepo.getAllItems()).thenReturn(localItems)
        }
    }

    protected fun verifyLocalItemsRetrieved() {
        runBlocking {
            verify(mockFeedRepo, Mockito.never()).getJsonFeed()
            verify(mockLocalRepo, Mockito.times(1)).getAllItems()
            verify(mockLocalRepo, Mockito.never()).insertItems(com.nhaarman.mockitokotlin2.any())
        }
    }

    protected fun verifyRemoteDataRetrieved() {
        runBlocking {
            verify(mockFeedRepo, Mockito.times(1)).getJsonFeed()
            verify(mockLocalRepo, Mockito.never()).getAllItems()
        }
    }

    protected fun verifyLiveDataChanged(uiModels: List<UiModel>) {
        verify(observer).onChanged(uiModels)
    }

    protected fun verifyDataStoredLocally(expectedLocalItems: List<LocalItem>) {
        runBlocking {
            verify(mockLocalRepo, Mockito.times(1)).insertItems(expectedLocalItems)
        }
    }

}