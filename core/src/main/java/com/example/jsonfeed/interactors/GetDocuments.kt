package com.example.jsonfeed.interactors

import com.example.jsonfeed.data.DocumentRepository

class GetDocuments(private val documentRepository: DocumentRepository) {
  suspend operator fun invoke() = documentRepository.getDocuments()
}
