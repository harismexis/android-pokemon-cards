package com.example.jsonfeed.mockproviders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jsonfeed.domain.LocalItem
import com.example.jsonfeed.presentation.detail.viewmodel.ItemDetailVm

import io.mockk.mockk

object MockItemDetailVmProvider {

    private var mockItemDetailVm: ItemDetailVm = mockk(relaxed = true)

    var mModel = MutableLiveData<LocalItem>()
    val model: LiveData<LocalItem>
        get() = mModel

    fun provideMockItemDetailVm(): ItemDetailVm {
        return mockItemDetailVm
    }

}