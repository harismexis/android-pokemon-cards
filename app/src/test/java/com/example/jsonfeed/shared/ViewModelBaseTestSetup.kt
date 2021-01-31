package com.example.jsonfeed.shared

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import com.example.jsonfeed.localdb.repository.LocalRepository
import com.example.jsonfeed.util.network.ConnectivityState
import com.example.jsonfeed.testutils.MainCoroutineScopeRule
import com.example.jsonfeed.util.network.ConnectivityMonitor

import com.jakewharton.rxrelay2.PublishRelay

import io.reactivex.plugins.RxJavaPlugins

import kotlinx.coroutines.ExperimentalCoroutinesApi

import org.junit.Rule

import org.mockito.Mock
import org.mockito.MockitoAnnotations

abstract class ViewModelBaseTestSetup {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Mock
    protected lateinit var mockLocalRepo: LocalRepository

    protected val connectivityUpdates: PublishRelay<ConnectivityState> = PublishRelay.create()

    abstract fun setupClassUnderTest()

    abstract fun setupMocks()

    protected fun doBeforeTest() {
        MockitoAnnotations.initMocks(this)
        setupMocks()
        setupClassUnderTest()
        setupRxErrorHandler()
    }

    protected fun setupRxErrorHandler() {
        RxJavaPlugins.setErrorHandler {
            // Do nothing
        }
    }

    private fun setupMockConnectivity() {

    }

    private fun setupMockResponse() {

    }

}