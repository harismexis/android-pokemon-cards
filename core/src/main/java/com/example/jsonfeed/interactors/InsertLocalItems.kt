package com.example.jsonfeed.interactors

import com.example.jsonfeed.data.LocalRepository
import com.example.jsonfeed.domain.LocalItem

class InsertLocalItems(
    private val repository: LocalRepository
) {
    suspend operator fun invoke(items: List<LocalItem>) = repository.insertItems(items)
}
