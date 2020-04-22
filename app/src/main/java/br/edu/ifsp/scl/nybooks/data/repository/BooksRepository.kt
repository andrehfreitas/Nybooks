package br.edu.ifsp.scl.nybooks.data.repository

import br.edu.ifsp.scl.nybooks.data.BooksResult

interface BooksRepository {
    fun getBooks(booksResultCallback: (result: BooksResult) -> Unit)
}