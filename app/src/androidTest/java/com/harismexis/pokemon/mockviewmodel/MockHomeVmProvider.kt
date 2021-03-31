package com.harismexis.pokemon.mockviewmodel

import com.harismexis.pokemon.ui.home.viewmodel.HomeVm
import io.mockk.mockk

object MockHomeVmProvider {

    private var mockHomeVm: HomeVm = mockk(relaxed = true)

    fun provideMockHomeVm(): HomeVm {
        return mockHomeVm
    }


}