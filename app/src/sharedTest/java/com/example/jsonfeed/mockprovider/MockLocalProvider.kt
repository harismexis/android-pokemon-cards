package com.example.jsonfeed.mockprovider

import com.example.jsonfeed.extensions.toLocalItems
import com.example.jsonfeed.localdb.LocalItem

fun provideMockLocalItems(): List<LocalItem> {
    return provideMockFeedValid().toLocalItems()
}
