package com.example.jsonfeed.mockprovider

import com.example.jsonfeed.extensions.toLocalItems
import com.example.jsonfeed.localdb.LocalItem

fun getMockLocalItemsFromFeedAllIdsValid(): List<LocalItem> {
    return getMockFeedAllIdsValid().toLocalItems()
}
