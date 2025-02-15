package com.harismexis.pokemon.detail

import androidx.lifecycle.Observer
import com.harismexis.pokemon.presentation.detail.viewmodel.ItemDetailVm
import com.harismexis.pokemon.shared.VmTestSetup
import com.harismexis.pokemon.domain.Item
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import kotlinx.coroutines.runBlocking
import org.mockito.Mock
import org.mockito.Mockito

abstract class ItemDetailVmTestSetup : VmTestSetup() {

    @Mock
    lateinit var mockObserver: Observer<Item>

    lateinit var mockItem: Item
    lateinit var mockId: String

    protected lateinit var detailVm: ItemDetailVm

    override fun initialise() {
        super.initialise()
        initialiseMockInteractors()
    }

    override fun initialiseClassUnderTest() {
        mockItem = mockParser.getMockItemValid()
        mockId = mockItem.id
        detailVm = ItemDetailVm(mockInteractors)
    }

    private fun initialiseMockInteractors() {
        Mockito.`when`(mockInteractors.iRRGetLocalItem).thenReturn(mockIRRGetLocalItem)
    }

    // Local Call

    protected fun mockLocalCall() {
        mockLocalCall(mockId, mockItem)
    }

    private fun mockLocalCall(
        itemId: String,
        item: Item
    ) {
        runBlocking {
            Mockito.`when`(mockIRRGetLocalItem.invoke(itemId)).thenReturn(item)
        }
    }

    protected fun mockLocalCallThrowsError() {
        runBlocking {
            Mockito.`when`(mockIRRGetLocalItem.invoke(any()))
                .thenThrow(IllegalStateException("Error"))
        }
    }

    protected fun verifyLocalCallDone() {
        runBlocking {
            verify(mockIRRGetLocalItem, Mockito.times(1)).invoke(mockId)
        }
    }

    // LiveData

    protected fun verifyLiveDataChanged() {
        verifyLiveDataChanged(mockItem)
    }

    private fun verifyLiveDataChanged(item: Item) {
        verify(mockObserver).onChanged(item)
    }

    protected fun verifyLiveDataNotChanged() {
        verifyZeroInteractions(mockObserver)
    }

    protected fun initialiseLiveData() {
        detailVm.model.observeForever(mockObserver)
    }

}