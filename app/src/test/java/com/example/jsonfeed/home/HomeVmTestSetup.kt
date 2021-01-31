package com.example.jsonfeed.home

import androidx.lifecycle.Observer
import com.example.jsonfeed.repository.FeedRepository
import com.example.jsonfeed.home.viewmodel.HomeVm
import com.example.jsonfeed.shared.ViewModelBaseTestSetup
import com.example.jsonfeed.uimodel.UiModel
import com.example.jsonfeed.util.network.ConnectivityMonitor

import org.mockito.Mock

abstract class HomeVmTestSetup : ViewModelBaseTestSetup() {

    @Mock
    protected lateinit var mockFeedRepo: FeedRepository

    @Mock
    protected lateinit var mockConnectivity: ConnectivityMonitor

    @Mock
    lateinit var observer: Observer<List<UiModel>>

    protected lateinit var homeVm: HomeVm

    override fun setupClassUnderTest() {
        homeVm = HomeVm(mockFeedRepo, mockLocalRepo, mockConnectivity)
        homeVm.models.observeForever(observer)
    }

    override fun setupMocks() {

    }

    protected fun setupMockFeedRepo() {

    }

    private fun setupMockConnectivity() {

    }

    private fun setupMockResponse() {

    }

}