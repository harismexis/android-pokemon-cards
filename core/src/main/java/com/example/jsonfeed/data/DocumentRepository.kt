package com.example.jsonfeed.data

import com.example.jsonfeed.domain.Document

class DocumentRepository(
    private val documentDataSource: DocumentDataSource
) {
    suspend fun addDocument(document: Document) = documentDataSource.add(document)

    suspend fun getDocuments() = documentDataSource.readAll()

    suspend fun removeDocument(document: Document) = documentDataSource.remove(document)
}