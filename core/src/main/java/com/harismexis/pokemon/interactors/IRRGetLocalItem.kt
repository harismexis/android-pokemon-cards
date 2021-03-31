package com.harismexis.pokemon.interactors

import com.harismexis.pokemon.data.LocalRepository

class IRRGetLocalItem(private val repository: LocalRepository) {

    suspend operator fun invoke(itemId: String) = repository.getItem(itemId)
}
