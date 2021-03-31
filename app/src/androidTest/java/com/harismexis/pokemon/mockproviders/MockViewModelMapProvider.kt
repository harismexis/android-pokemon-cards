package com.harismexis.pokemon.mockproviders

import androidx.lifecycle.ViewModel

import com.harismexis.pokemon.detail.viewmodel.ItemDetailVm
import com.harismexis.pokemon.home.viewmodel.HomeVm

import javax.inject.Provider

fun provideMockVmMap(): MutableMap<Class<out ViewModel>, Provider<ViewModel>> {
    val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>> = mutableMapOf()
    viewModels[HomeVm::class.java] = Provider { MockHomeVmProvider.provideMockHomeVm() }
    viewModels[ItemDetailVm::class.java] = Provider { MockItemDetailVmProvider.provideMockItemDetailVm() }
    return viewModels
}