package com.harismexis.pokemon.instrumentedsetup.mockvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.harismexis.pokemon.domain.Item
import com.harismexis.pokemon.presentation.detail.viewmodel.ItemDetailVm

import io.mockk.mockk

object MockItemDetailVmProvider {

    private var mockItemDetailVm: ItemDetailVm = mockk(relaxed = true)

    var mModel = MutableLiveData<Item>()
    val model: LiveData<Item>
        get() = mModel

    fun provideMockItemDetailVm(): ItemDetailVm {
        return mockItemDetailVm
    }

}