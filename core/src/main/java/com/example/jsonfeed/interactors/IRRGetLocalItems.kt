package com.example.jsonfeed.interactors

import com.example.jsonfeed.data.LocalRepository

class IRRGetLocalItems(
    private val repository: LocalRepository
) {
    suspend operator fun invoke() = repository.getItems()
}
