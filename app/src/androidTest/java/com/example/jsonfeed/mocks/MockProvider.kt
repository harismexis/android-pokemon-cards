package com.example.jsonfeed.mocks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.example.jsonfeed.home.viewmodel.HomeVm
import com.example.jsonfeed.uimodel.UiModel
import io.mockk.mockk

object MockProvider {

    private var mockViewModel: HomeVm = mockk(relaxed = true)

    var mEmptyModels = MutableLiveData<List<UiModel>>()
    val emptyModels: LiveData<List<UiModel>>
        get() = mEmptyModels

    var mModels = MutableLiveData<List<UiModel>>()
    val models: LiveData<List<UiModel>>
        get() = mModels

    fun provideMockViewModel(): HomeVm {
        return mockViewModel
    }
}