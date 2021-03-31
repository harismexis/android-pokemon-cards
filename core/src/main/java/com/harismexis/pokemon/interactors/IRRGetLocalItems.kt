package com.harismexis.pokemon.interactors

import com.harismexis.pokemon.data.LocalRepository

class IRRGetLocalItems(
    private val repository: LocalRepository
) {
    suspend operator fun invoke() = repository.getItems()
}
