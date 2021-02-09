package com.example.jsonfeed.interactors

import com.example.jsonfeed.data.DocumentRepository
import com.example.jsonfeed.domain.Document

class RemoveDocument(private val documentRepository: DocumentRepository) {
  suspend operator fun invoke(document: Document) = documentRepository.removeDocument(document)
}