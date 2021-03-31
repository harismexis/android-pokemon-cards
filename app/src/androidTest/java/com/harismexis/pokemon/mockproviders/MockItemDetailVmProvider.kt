package com.harismexis.pokemon.mockproviders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.harismexis.pokemon.detail.viewmodel.ItemDetailVm
import com.harismexis.pokemon.uimodel.UiModel

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