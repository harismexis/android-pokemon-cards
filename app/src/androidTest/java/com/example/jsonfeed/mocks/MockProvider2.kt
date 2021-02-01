package com.example.jsonfeed.mocks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jsonfeed.detail.viewmodel.ItemDetailVm

import com.example.jsonfeed.home.viewmodel.HomeVm
import com.example.jsonfeed.uimodel.UiModel
import io.mockk.mockk

import org.mockito.Mockito

object MockProvider2 {

    private var mockViewModel: ItemDetailVm = mockk(relaxed = true)

    val mModel = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() = mModel

    fun provideMockViewModel(): ItemDetailVm {
        return mockViewModel
    }
}