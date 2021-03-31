package com.harismexis.pokemon.shared

import com.harismexis.pokemon.framework.Interactors
import com.harismexis.pokemon.interactors.IRRGetLocalItem
import com.harismexis.pokemon.interactors.IRRGetLocalItems
import com.harismexis.pokemon.interactors.IRRGetRemoteItems
import com.harismexis.pokemon.interactors.IRRStoreItems
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