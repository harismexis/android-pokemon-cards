package com.harismexis.pokemon.interactors

import com.harismexis.pokemon.data.LocalRepository
import com.harismexis.pokemon.domain.Item

class IRRStoreItems(
    private val repository: LocalRepository
) {
    suspend operator fun invoke(items: List<Item>) = repository.insertItems(items)
}
