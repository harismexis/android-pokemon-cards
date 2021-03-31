package com.harismexis.pokemon.framework

import com.harismexis.pokemon.interactors.IRRGetLocalItem
import com.harismexis.pokemon.interactors.IRRGetLocalItems
import com.harismexis.pokemon.interactors.IRRGetRemoteItems
import com.harismexis.pokemon.interactors.IRRStoreItems

data class Interactors(
    val iRRGetLocalItem: IRRGetLocalItem,
    val iRRGetLocalItems: IRRGetLocalItems,
    val iRRGetRemoteItems: IRRGetRemoteItems,
    val iRRStoreItems: IRRStoreItems
)
