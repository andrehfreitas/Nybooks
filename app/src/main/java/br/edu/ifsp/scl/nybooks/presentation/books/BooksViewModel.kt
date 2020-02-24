package br.edu.ifsp.scl.nybooks.presentation.books

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.ifsp.scl.nybooks.data.model.Book

class BooksViewModel: ViewModel() {

    val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()

    fun getBooks(){
        booksLiveData.value = createFakeBooks()
    }


    fun createFakeBooks(): List<Book> {
        return listOf(
            Book("Titulo 1", "Author 1"),
            Book("Titulo 2", "Author 2"),
            Book("Titulo 3", "Author 3")
        )
    }
}