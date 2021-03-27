package com.example.jsonfeed.mockviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jsonfeed.datamodel.uimodel.PokemonUiModel
import com.example.jsonfeed.ui.home.viewmodel.HomeVm
import io.mockk.mockk

object MockHomeVmProvider {

    private var mockHomeVm: HomeVm = mockk(relaxed = true)

    var mModels = MutableLiveData<List<PokemonUiModel>>()
    val models: LiveData<List<PokemonUiModel>>
        get() = mModels

    fun provideMockHomeVm(): HomeVm {
        return mockHomeVm
    }


}