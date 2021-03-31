package com.harismexis.pokemon.mockprovider

import com.harismexis.pokemon.extensions.toLocalItems
import com.harismexis.pokemon.localdb.LocalItem

fun getMockLocalItemsFromFeedAllIdsValid(): List<LocalItem> {
    return getMockFeedAllIdsValid().toLocalItems()
}
