package com.example.jsonfeed.interactors

import com.example.jsonfeed.data.LocalRepository

class IRRGetLocalItem(private val repository: LocalRepository) {

    suspend operator fun invoke(itemId: String) = repository.getItem(itemId)
}
