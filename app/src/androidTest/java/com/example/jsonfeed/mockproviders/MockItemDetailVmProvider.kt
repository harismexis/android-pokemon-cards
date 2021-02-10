package com.example.jsonfeed.mockproviders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.example.jsonfeed.uimodel.UiModel

import io.mockk.mockk

object MockItemDetailVmProvider {

    private var mockItemDetailVm: ItemDetailVm = mockk(relaxed = true)

    var mModel = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() = mModel

    fun provideMockItemDetailVm(): ItemDetailVm {
        return mockItemDetailVm
    }

}