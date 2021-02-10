package com.example.jsonfeed.framework

import com.example.jsonfeed.interactors.GetLocalFeedItem
import com.example.jsonfeed.interactors.GetLocalFeedItems
import com.example.jsonfeed.interactors.GetRemoteFeed
import com.example.jsonfeed.interactors.InsertLocalFeedItems

data class Interactors(
    val getLocalFeedItem: GetLocalFeedItem,
    val getLocalFeedItems: GetLocalFeedItems,
    val getRemoteFeed: GetRemoteFeed,
    val insertLocalFeedItems: InsertLocalFeedItems
)
