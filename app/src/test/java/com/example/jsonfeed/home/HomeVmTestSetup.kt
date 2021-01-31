package com.example.jsonfeed.home

import com.example.jsonfeed.repository.FeedRepository
import com.example.jsonfeed.home.viewmodel.HomeVm
import com.example.jsonfeed.shared.ViewModelBaseTestSetup
import com.example.jsonfeed.util.network.ConnectivityMonitor

import org.mockito.Mock

abstract class HomeVmTestSetup : ViewModelBaseTestSetup() {

    @Mock
    protected lateinit var mockFeedRepo: FeedRepository

    @Mock
    protected lateinit var mockConnectivity: ConnectivityMonitor

    protected lateinit var homeVm: HomeVm

    override fun setupClassUnderTest() {
        homeVm = HomeVm(mockFeedRepo, mockLocalRepo, mockConnectivity)
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