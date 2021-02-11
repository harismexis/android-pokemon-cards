package com.example.jsonfeed.shared

import com.example.jsonfeed.base.BaseTestSetup
import com.example.jsonfeed.framework.Interactors
import com.example.jsonfeed.interactors.GetLocalItem
import com.example.jsonfeed.interactors.GetLocalItems
import com.example.jsonfeed.interactors.GetRemoteItems
import com.example.jsonfeed.interactors.StoreItems
import com.example.jsonfeed.util.UnitTestMockParser
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

abstract class UnitTestSetup : BaseTestSetup() {

    @Mock
    protected lateinit var mockInteractorGetLocalItem: GetLocalItem
    @Mock
    protected lateinit var mockInteractorGetLocalItems: GetLocalItems
    @Mock
    protected lateinit var mockInteractorGetRemoteItems: GetRemoteItems
    @Mock
    protected lateinit var mockInteractorStoreItems: StoreItems
    @Mock
    protected lateinit var mockInteractors: Interactors

    protected val mockParser = UnitTestMockParser()

    open fun initialise() {
        MockitoAnnotations.initMocks(this)
        initialiseMockInteractors()
        initialiseClassUnderTest()
    }

    abstract fun initialiseClassUnderTest()

    open fun initialiseMockInteractors() {
//        Mockito.`when`(mockInteractors.getRemoteItems).thenReturn(mockInteractorGetRemoteItems)
//        Mockito.`when`(mockInteractors.getLocalItems).thenReturn(mockInteractorGetLocalItems)
//        Mockito.`when`(mockInteractors.getLocalItem).thenReturn(mockInteractorGetLocalItem)
//        Mockito.`when`(mockInteractors.storeItems).thenReturn(mockInteractorStoreItems)
    }

}