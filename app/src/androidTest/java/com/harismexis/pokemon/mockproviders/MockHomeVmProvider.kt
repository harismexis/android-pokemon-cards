package com.harismexis.pokemon.mockproviders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.harismexis.pokemon.home.viewmodel.HomeVm
import com.harismexis.pokemon.uimodel.UiModel

import io.mockk.mockk

object MockHomeVmProvider {

    private var mockHomeVm: HomeVm = mockk(relaxed = true)

    var mModels = MutableLiveData<List<UiModel>>()
    val models: LiveData<List<UiModel>>
        get() = mModels

    fun provideMockHomeVm(): HomeVm {
        return mockHomeVm
    }


}