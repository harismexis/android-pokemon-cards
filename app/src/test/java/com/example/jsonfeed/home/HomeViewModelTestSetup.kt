package com.example.jsonfeed.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import com.example.jsonfeed.home.repository.FeedRepository
import com.example.jsonfeed.home.viewmodel.HomeVm
import com.example.jsonfeed.localdb.repository.LocalRepository
import com.example.jsonfeed.util.network.ConnectivityState
import com.example.jsonfeed.testutils.MainCoroutineScopeRule

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
    protected lateinit var mockFeedRepo: FeedRepository

    @Mock
    protected lateinit var mockLocalRepo: LocalRepository

    protected val connectivityUpdates: PublishRelay<ConnectivityState> = PublishRelay.create()

    protected lateinit var homeVm: HomeVm

    protected fun setupClassUnderTest() {
        homeVm = HomeVm(mockFeedRepo, mockLocalRepo)
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