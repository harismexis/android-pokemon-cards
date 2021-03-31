package com.harismexis.pokemon.detail

import androidx.lifecycle.Observer
import com.harismexis.pokemon.shared.ViewModelBaseTestSetup
import com.harismexis.pokemon.detail.viewmodel.ItemDetailVm
import com.harismexis.pokemon.localdb.LocalItem
import com.harismexis.pokemon.uimodel.UiModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
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

    protected fun verifyLiveDataNotChanged() {
        verifyZeroInteractions(observer)
    }

    protected fun mockLocalItemCallThrowsError() {
        runBlocking {
            Mockito.`when`(mockLocalRepo.getItemById(any()))
                .thenThrow(IllegalStateException("Error"))
        }
    }

}