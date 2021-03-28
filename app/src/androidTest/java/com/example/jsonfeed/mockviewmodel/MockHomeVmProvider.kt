package com.example.jsonfeed.mockviewmodel

import com.example.jsonfeed.ui.home.viewmodel.HomeVm
import io.mockk.mockk

object MockHomeVmProvider {

    private var mockHomeVm: HomeVm = mockk(relaxed = true)

    fun provideMockHomeVm(): HomeVm {
        return mockHomeVm
    }


}