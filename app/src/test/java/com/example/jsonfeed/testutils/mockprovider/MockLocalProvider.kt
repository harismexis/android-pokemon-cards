package com.example.jsonfeed.testutils.mockprovider

import com.example.jsonfeed.extensions.toLocalItems
import com.example.jsonfeed.extensions.toUiModels
import com.example.jsonfeed.localdb.LocalItem
import com.example.jsonfeed.testutils.mockprovider.provideMockFeedValid

fun provideMockLocalItemsValid(): List<LocalItem> {
    return provideMockFeedValid().toUiModels().toLocalItems()
}
