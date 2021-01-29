package com.example.jsonfeed.detail

import com.example.jsonfeed.detail.viewmodel.ItemDetailVm
import com.example.jsonfeed.shared.ViewModelBaseTestSetup

abstract class ItemDetailVmTestSetup : ViewModelBaseTestSetup() {

    protected lateinit var itemDetailVm: ItemDetailVm

    override fun setupClassUnderTest() {
        itemDetailVm = ItemDetailVm(mockLocalRepo)
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