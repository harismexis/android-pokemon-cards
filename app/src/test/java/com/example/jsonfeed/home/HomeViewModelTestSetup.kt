package com.example.jsonfeed.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import com.example.jsonfeed.home.repository.FeedRepo
import com.example.jsonfeed.home.viewmodel.HomeVm
import com.example.jsonfeed.workshared.util.network.ConnectivityMonitor
import com.example.jsonfeed.workshared.util.network.ConnectivityState
import com.example.scoredonut.testutils.MainCoroutineScopeRule
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.plugins.RxJavaPlugins
import kotlinx.coroutines.ExperimentalCoroutinesApi

import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.`when`

open class HomeViewModelTestSetup {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    companion object {
        const val SCORE = 500
        const val MAX_SCORE = 700
    }

    @Mock
    protected lateinit var mockFeedRepo: FeedRepo

//    @Mock
//    protected lateinit var mockConnectivityMonitor: ConnectivityMonitor

//    @Mock
//    protected lateinit var mockResponse: CreditResponse
//
//    @Mock
//    protected lateinit var mockCreditInfo: CreditReportInfo
//
//    @Mock
//    lateinit var observer: Observer<CreditUiModel>

    protected  val connectivityUpdates: PublishRelay<ConnectivityState> =
        PublishRelay.create()

    protected  lateinit var homeVm: HomeVm

    protected fun setupClassUnderTest() {
        // homeVm = HomeVm(mockFeedRepo)
        // mainViewModel.creditUiModel.observeForever(observer)
    }

    protected fun setupMocks() {
        setupMockResponse()
        // setupMockConnectivity()
        // setupMockCreditRepository(mockResponse)
    }

//    protected fun setupMockCreditRepository(creditResponse: CreditResponse) {
//        runBlocking {
//        `when`(mockCreditRepository.getCreditScore()).thenReturn(creditResponse)
//        }
//    }

    protected fun setupRxErrorHandler() {
        RxJavaPlugins.setErrorHandler {
            // do nothing
        }
    }

//    private fun setupMockConnectivity() {
//        `when`(mockConnectivityMonitor.getConnectivityUpdates()).thenReturn(
//            connectivityUpdates
//        )
//    }

    private fun setupMockResponse() {
//        `when`(mockCreditInfo.score).thenReturn(SCORE)
//        `when`(mockCreditInfo.maxScoreValue).thenReturn(MAX_SCORE)
//        `when`(mockResponse.creditReportInfo).thenReturn(mockCreditInfo)
    }

}