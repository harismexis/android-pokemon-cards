package com.example.jsonfeed.shared

import com.example.jsonfeed.framework.Interactors
import com.example.jsonfeed.interactors.GetLocalItem
import com.example.jsonfeed.interactors.GetLocalItems
import com.example.jsonfeed.interactors.GetRemoteItems
import com.example.jsonfeed.interactors.StoreItems
import org.mockito.Mock

abstract class VmTestSetup : UnitTestSetup() {

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

}