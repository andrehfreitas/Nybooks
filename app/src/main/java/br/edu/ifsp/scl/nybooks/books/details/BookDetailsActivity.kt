package br.edu.ifsp.scl.nybooks.books.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import br.edu.ifsp.scl.nybooks.R
import br.edu.ifsp.scl.nybooks.books.base.BaseActivity
import kotlinx.android.synthetic.main.activity_book_details.*
import kotlinx.android.synthetic.main.include_toolbar.*

class BookDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        // Toolbar personalizada substituindo a padrão do android
        setupToolbar(toolbarApp, R.string.books_details_title, true)

        bookDetailsTitle.text = intent.getStringExtra(EXTRA_TITLE)
        bookDetailsDescription.text = intent.getStringExtra(EXTRA_DESCRIPTION)
    }

// Retorno de Intent para a activity origem (pode ser qualquer activity do projeto) que chamar a Books Detail Activity
    companion object {
        private const val EXTRA_TITLE = "EXTRA_TITLE"
        private const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"

        fun getStartIntent (context: Context, title: String, description: String): Intent {
            return Intent (context, BookDetailsActivity::class.java).apply {
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_DESCRIPTION, description)
            }
        }
    }
}
