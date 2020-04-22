package br.edu.ifsp.scl.nybooks.books.books

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.nybooks.R
import br.edu.ifsp.scl.nybooks.data.repository.BooksApiDataSource
import br.edu.ifsp.scl.nybooks.books.base.BaseActivity
import br.edu.ifsp.scl.nybooks.books.details.BookDetailsActivity
import kotlinx.android.synthetic.main.activity_books.*
import kotlinx.android.synthetic.main.include_toolbar.*

class BooksActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        // Toolbar personalizada substituindo a padrão do android
        setupToolbar(toolbarApp, R.string.books_title)

        // Instanciação do ViewModel para receber os dados na activity
        val viewModel = BooksViewModel.ViewModelFactory (BooksApiDataSource()).create(BooksViewModel::class.java)

        viewModel.booksLiveData.observe(this, Observer {
                it?.let { books ->
                    with(recyclerBooks){
                        layoutManager = LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
                        setHasFixedSize(true)

                        // Uso de lambda para chamar a activity BooksDetailActivity passando dados de book
                        adapter = BooksAdapter(books) { book ->
                            val intent = BookDetailsActivity.getStartIntent(this@BooksActivity, book.title, book.description)
                            this@BooksActivity.startActivity(intent)
                        }
                    }
                }
            }
        )

        viewModel.viewFlipperLiveData.observe(this, Observer {
            it?.let { viewFlipper ->
                viewFlipperBooks.displayedChild = viewFlipper.first

                viewFlipper.second?.let { errorMessageResId ->
                    textViewError.text = getString(errorMessageResId)
                }
            }
        })

        viewModel.getBooks()
    }

}
