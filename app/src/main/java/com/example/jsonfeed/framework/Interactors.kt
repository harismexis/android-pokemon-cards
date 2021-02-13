package com.example.jsonfeed.framework

import com.example.jsonfeed.interactors.*

data class Interactors(
    val iRRGetLocalItem: IRRGetLocalItem,
    val iRRGetLocalItems: IRRGetLocalItems,
    val iRRGetRemoteItems: IRRGetRemoteItems,
    val iRRStoreItems: IRRStoreItems
)
