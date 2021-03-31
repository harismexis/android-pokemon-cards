package com.harismexis.pokemon.instrumentedsetup.mockvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.harismexis.pokemon.domain.Item
import com.harismexis.pokemon.presentation.home.viewmodel.HomeVm

import io.mockk.mockk

object MockHomeVmProvider {

    private var mockHomeVm: HomeVm = mockk(relaxed = true)

    var mModels = MutableLiveData<List<Item>>()
    val models: LiveData<List<Item>>
        get() = mModels

    fun provideMockHomeVm(): HomeVm {
        return mockHomeVm
    }


}