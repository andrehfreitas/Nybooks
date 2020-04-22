package br.edu.ifsp.scl.nybooks.presentation.books

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.edu.ifsp.scl.nybooks.R
import br.edu.ifsp.scl.nybooks.books.books.BooksViewModel
import br.edu.ifsp.scl.nybooks.data.BooksResult
import br.edu.ifsp.scl.nybooks.data.model.Book
import br.edu.ifsp.scl.nybooks.data.repository.BooksRepository
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BooksViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var booksLiveDataObserver: Observer<List<Book>>

    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int, Int?>>

    private lateinit var viewModel: BooksViewModel

    @Test
    fun `when view model getBooks get success then sets bookLiveData`() {
        // Arrange
        val books = listOf(
            Book ("Titulo 1", "Autor 1", "Descricao 1")
        )
        val resultSuccess = MockRepository(BooksResult.Success(books))
        viewModel = BooksViewModel(resultSuccess)
        viewModel.booksLiveData.observeForever(booksLiveDataObserver)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(booksLiveDataObserver).onChanged(books)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(1, null))
    }

    @Test
    fun `when view model getBooks get Api error then sets viewFlipperLiveData`(){
        //Arrange
        val errorCode = 401
        val resultApiError = MockRepository(BooksResult.ApiError(errorCode))
        viewModel = BooksViewModel(resultApiError)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getBooks()

        // Assert
        when (errorCode) {
            401 -> verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.books_error_401))
            else -> verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.books_error_400_generic))
        }

    }


    @Test
    fun `when view model getBooks get server error then sets viewFlipperLiveData`(){
        //Arrange
        val resultServerErro = MockRepository(BooksResult.ServerError)
        viewModel = BooksViewModel(resultServerErro)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getBooks()

        //Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.books_error_500_generic))
    }
}

class MockRepository(private val result: BooksResult): BooksRepository {
    override fun getBooks(booksResultCallback: (result: BooksResult) -> Unit) {
        booksResultCallback(result)
    }
}