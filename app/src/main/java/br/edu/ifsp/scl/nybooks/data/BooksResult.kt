package br.edu.ifsp.scl.nybooks.data

import br.edu.ifsp.scl.nybooks.data.model.Book

sealed class BooksResult {
    class Success (val books: List<Book>): BooksResult()
    class ApiError (val statusCode: Int): BooksResult()
    object ServerError: BooksResult()
}