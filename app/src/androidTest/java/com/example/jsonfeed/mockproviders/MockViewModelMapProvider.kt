package com.example.jsonfeed.mockproviders

import androidx.lifecycle.ViewModel

import com.example.jsonfeed.detail.viewmodel.ItemDetailVm
import com.example.jsonfeed.home.viewmodel.HomeVm

import javax.inject.Provider

fun provideMockVmMap(): MutableMap<Class<out ViewModel>, Provider<ViewModel>> {
    val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>> = mutableMapOf()
    viewModels[HomeVm::class.java] = Provider { MockHomeVmProvider.provideMockHomeVm() }
    viewModels[ItemDetailVm::class.java] = Provider { MockItemDetailVmProvider.provideMockItemDetailVm() }
    return viewModels
}