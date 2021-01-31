package com.example.jsonfeed.testutils

import com.example.jsonfeed.datamodel.Feed
import com.example.jsonfeed.extensions.toLocalItems
import com.example.jsonfeed.extensions.toUiModels
import com.example.jsonfeed.localdb.LocalItem

fun provideMockLocalItemsValid(): List<LocalItem> {
    return provideMockFeedValid().toUiModels().toLocalItems()
}