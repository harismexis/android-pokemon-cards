package com.example.jsonfeed.home

import com.example.jsonfeed.home.repository.FeedRepository
import com.example.jsonfeed.home.viewmodel.HomeVm
import com.example.jsonfeed.shared.ViewModelBaseTestSetup

import org.mockito.Mock

abstract class HomeVmTestSetup : ViewModelBaseTestSetup() {

    @Mock
    protected lateinit var mockFeedRepo: FeedRepository

    protected lateinit var homeVm: HomeVm

    override fun setupClassUnderTest() {
        homeVm = HomeVm(mockFeedRepo, mockLocalRepo)
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