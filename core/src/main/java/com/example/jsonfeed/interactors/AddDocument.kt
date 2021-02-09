package com.example.jsonfeed.interactors

import com.example.jsonfeed.domain.Document
import com.example.jsonfeed.data.DocumentRepository

class AddDocument(private val documentRepository: DocumentRepository) {
  suspend operator fun invoke(document: Document) = documentRepository.addDocument(document)
}
