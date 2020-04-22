package br.edu.ifsp.scl.nybooks.data.repository

import br.edu.ifsp.scl.nybooks.data.ApiService
import br.edu.ifsp.scl.nybooks.data.BooksResult
import br.edu.ifsp.scl.nybooks.data.model.Book
import br.edu.ifsp.scl.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksApiDataSource: BooksRepository {
    override fun getBooks(booksResultCallback: (result: BooksResult) -> Unit){
        ApiService.service.getBooks().enqueue(object: Callback<BookBodyResponse> {
            override fun onResponse(call: Call<BookBodyResponse>, response: Response<BookBodyResponse>) {
                when {
                    response.isSuccessful -> {
                        val books: MutableList<Book> = mutableListOf()

                        response.body()?.let { bookBodyResponse ->
                            for (result in bookBodyResponse.bookResults){
                                val book = result.bookDetailResponses[0].getBooksModel()
                                books.add(book)
                            }
                        }
                        booksResultCallback(BooksResult.Success(books))
                    }
                    else ->  booksResultCallback(BooksResult.ApiError(response.code()))
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                booksResultCallback(BooksResult.ServerError)
            }

        })
    }
}