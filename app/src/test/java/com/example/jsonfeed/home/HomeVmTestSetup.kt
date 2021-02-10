//package com.example.jsonfeed.home
//
//import androidx.lifecycle.Observer
//
//import com.example.jsonfeed.framework.datamodel.Feed
//import com.example.jsonfeed.repository.FeedRepository
//import com.example.jsonfeed.home.viewmodel.HomeVm
//import com.example.jsonfeed.shared.ViewModelBaseTestSetup
//import com.example.jsonfeed.uimodel.UiModel
//import com.example.jsonfeed.framework.util.network.ConnectivityMonitor
//import com.example.jsonfeed.presentation.home.viewmodel.HomeVm
//import com.nhaarman.mockitokotlin2.any
//
//import com.nhaarman.mockitokotlin2.verify
//import com.nhaarman.mockitokotlin2.verifyZeroInteractions
//
//import kotlinx.coroutines.runBlocking
//
//import org.mockito.Mock
//import org.mockito.Mockito
//
//abstract class HomeVmTestSetup : ViewModelBaseTestSetup() {
//
//    @Mock
//    protected lateinit var mockFeedRepo: FeedRepository
//
//    @Mock
//    protected lateinit var mockConnectivity: ConnectivityMonitor
//
//    @Mock
//    lateinit var observer: Observer<List<UiModel>>
//
//    protected lateinit var homeVm: HomeVm
//
//    override fun initialiseClassUnderTest() {
//        homeVm = HomeVm(mockFeedRepo, mockLocalRepo, mockConnectivity)
//        homeVm.models.observeForever(observer)
//    }
//
//    protected fun mockInternetActive(active: Boolean) {
//        Mockito.`when`(mockConnectivity.isOnline()).thenReturn(active)
//    }
//
//    protected fun mockRemoteFeedCall(feed: Feed) {
//        runBlocking {
//            Mockito.`when`(mockFeedRepo.getJsonFeed()).thenReturn(feed)
//        }
//    }
//
//    protected fun mockRemoteFeedCallThrowsError() {
//        runBlocking {
//            Mockito.`when`(mockFeedRepo.getJsonFeed())
//                .thenThrow(IllegalStateException("Error"))
//        }
//    }
//
//    protected fun mockLocalFeedCall(items: List<LocalItem>) {
//        runBlocking {
//            Mockito.`when`(mockLocalRepo.getAllItems()).thenReturn(items)
//        }
//    }
//
//    protected fun mockLocalFeedCallThrowsError() {
//        runBlocking {
//            Mockito.`when`(mockLocalRepo.getAllItems())
//                .thenThrow(IllegalStateException("Error"))
//        }
//    }
//
//    protected fun verifyRemoteFeedCallDone() {
//        runBlocking {
//            verify(mockFeedRepo, Mockito.times(1)).getJsonFeed()
//        }
//    }
//
//    protected fun verifyRemoteFeedCallNotDone() {
//        runBlocking {
//            verify(mockFeedRepo, Mockito.never()).getJsonFeed()
//        }
//    }
//
//    protected fun verifyLocalFeedCallDone() {
//        runBlocking {
//            verify(mockLocalRepo, Mockito.times(1)).getAllItems()
//        }
//    }
//
//    protected fun verifyLocalFeedCallNotDone() {
//        runBlocking {
//            verify(mockLocalRepo, Mockito.never()).getAllItems()
//        }
//    }
//
//    protected fun verifyLiveDataChanged(models: List<UiModel>) {
//        verify(observer).onChanged(models)
//    }
//
//    protected fun verifyLiveDataNotChanged() {
//        verify(observer, Mockito.never()).onChanged(any())
//        verifyZeroInteractions(observer)
//    }
//
//    protected fun verifyDataStoredLocally(items: List<LocalItem>) {
//        runBlocking {
//            verify(mockLocalRepo, Mockito.times(1)).insertItems(items)
//        }
//    }
//
//    protected fun verifyDataNotStoredLocally() {
//        runBlocking {
//            verify(mockLocalRepo, Mockito.never()).insertItems(any())
//        }
//    }
//
//    protected fun verifyInternetChecked() {
//        verify(mockConnectivity, Mockito.times(1)).isOnline()
//    }
//
//}