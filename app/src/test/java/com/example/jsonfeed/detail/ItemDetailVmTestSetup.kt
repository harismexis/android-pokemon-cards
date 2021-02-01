package com.example.jsonfeed.detail

import androidx.lifecycle.Observer

import com.example.jsonfeed.detail.viewmodel.ItemDetailVm
import com.example.jsonfeed.localdb.LocalItem
import com.example.jsonfeed.shared.ViewModelBaseTestSetup
import com.example.jsonfeed.uimodel.UiModel

import com.nhaarman.mockitokotlin2.verify

import kotlinx.coroutines.runBlocking

import org.mockito.Mock
import org.mockito.Mockito

abstract class ItemDetailVmTestSetup : ViewModelBaseTestSetup() {

    @Mock
    lateinit var observer: Observer<UiModel>

    protected lateinit var detailVm: ItemDetailVm

    override fun initialiseClassUnderTest() {
        detailVm = ItemDetailVm(mockLocalRepo)
        detailVm.model.observeForever(observer)
    }

    protected fun mockLocalItemCall(
        itemId: String,
        localItem: LocalItem
    ) {
        runBlocking {
            Mockito.`when`(mockLocalRepo.getItemById(itemId)).thenReturn(localItem)
        }
    }

    protected fun verifyLocalItemRetrieved(itemId: String) {
        runBlocking {
            verify(mockLocalRepo, Mockito.times(1)).getItemById(itemId)
        }
    }

    protected fun verifyLiveDataChanged(uiModel: UiModel) {
        verify(observer).onChanged(uiModel)
    }

}