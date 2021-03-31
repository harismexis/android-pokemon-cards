package com.harismexis.pokemon.mockviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.harismexis.pokemon.datamodel.uimodel.PokemonUiModel
import com.harismexis.pokemon.ui.home.viewmodel.HomeVm
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