package com.example.jsonfeed.detail

import androidx.lifecycle.Observer
import com.example.jsonfeed.detail.viewmodel.ItemDetailVm
import com.example.jsonfeed.shared.ViewModelBaseTestSetup
import com.example.jsonfeed.uimodel.UiModel
import org.mockito.Mock

abstract class ItemDetailVmTestSetup : ViewModelBaseTestSetup() {

    @Mock
    lateinit var observer: Observer<UiModel>

    protected lateinit var detailVm: ItemDetailVm

    override fun setupClassUnderTest() {
        detailVm = ItemDetailVm(mockLocalRepo)
        detailVm.model.observeForever(observer)
    }

}