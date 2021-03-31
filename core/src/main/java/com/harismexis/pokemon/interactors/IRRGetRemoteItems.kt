package com.harismexis.pokemon.interactors

import com.harismexis.pokemon.data.RemoteRepository

class IRRGetRemoteItems(
    private val repository: RemoteRepository
) {
    suspend operator fun invoke() = repository.getItems()
}
