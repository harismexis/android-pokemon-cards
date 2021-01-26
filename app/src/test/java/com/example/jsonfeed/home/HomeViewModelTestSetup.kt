package com.example.jsonfeed.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import com.example.jsonfeed.home.repository.FeedRepo
import com.example.jsonfeed.home.viewmodel.HomeVm
import com.example.jsonfeed.workshared.util.network.ConnectivityState
import com.example.scoredonut.testutils.MainCoroutineScopeRule
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.plugins.RxJavaPlugins
import kotlinx.coroutines.ExperimentalCoroutinesApi

import org.junit.Rule
import org.mockito.Mock

open class HomeViewModelTestSetup {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Mock
    protected lateinit var mockFeedRepo: FeedRepo

    protected val connectivityUpdates: PublishRelay<ConnectivityState> = PublishRelay.create()

    protected lateinit var homeVm: HomeVm

    protected fun setupClassUnderTest() {
        homeVm = HomeVm(mockFeedRepo)
    }

    protected fun setupMocks() {

    }

    protected fun setupMockFeedRepo() {

    }

    protected fun setupRxErrorHandler() {
        RxJavaPlugins.setErrorHandler {
            // do nothing
        }
    }

    private fun setupMockConnectivity() {

    }

    private fun setupMockResponse() {

    }

}