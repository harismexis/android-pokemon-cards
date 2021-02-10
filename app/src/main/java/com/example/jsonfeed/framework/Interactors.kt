package com.example.jsonfeed.framework

import com.example.jsonfeed.interactors.GetLocalItem
import com.example.jsonfeed.interactors.GetLocalItems
import com.example.jsonfeed.interactors.GetRemoteFeed
import com.example.jsonfeed.interactors.InsertLocalItems

data class Interactors(
    val getLocalFeedItem: GetLocalItem,
    val getLocalFeedItems: GetLocalItems,
    val getRemoteFeed: GetRemoteFeed,
    val insertLocalFeedItems: InsertLocalItems
)
