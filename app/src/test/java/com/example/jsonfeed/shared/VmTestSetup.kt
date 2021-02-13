package com.example.jsonfeed.shared

import com.example.jsonfeed.framework.Interactors
import com.example.jsonfeed.interactors.*
import org.mockito.Mock

abstract class VmTestSetup : UnitTestSetup() {

    @Mock
    protected lateinit var mockIRRGetLocalItem: IRRGetLocalItem
    @Mock
    protected lateinit var mockIRRGetLocalItems: IRRGetLocalItems
    @Mock
    protected lateinit var mockIRRGetRemoteItems: IRRGetRemoteItems
    @Mock
    protected lateinit var mockIRRStoreItems: IRRStoreItems
    @Mock
    protected lateinit var mockInteractors: Interactors

}