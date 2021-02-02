package com.example.jsonfeed.mockproviders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.example.jsonfeed.home.viewmodel.HomeVm
import com.example.jsonfeed.uimodel.UiModel

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