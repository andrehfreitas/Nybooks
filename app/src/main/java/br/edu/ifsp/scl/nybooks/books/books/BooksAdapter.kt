package br.edu.ifsp.scl.nybooks.books.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.nybooks.R
import br.edu.ifsp.scl.nybooks.data.model.Book
import kotlinx.android.synthetic.main.item_book.view.*

class BooksAdapter(private val books: List<Book>, private val onItemClickListener: ((book: Book) -> Unit)):
    RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {


    class BooksViewHolder(itemView: View, private val onItemClickListener: ((book: Book) -> Unit)):
        RecyclerView.ViewHolder(itemView){

        private val title = itemView.textTitle
        private val author = itemView.textAuthor

        fun bindView(book: Book){
            title.text = book.title
            author.text = book.author

            // Implementação do evento de toque em um item da lista
            itemView.setOnClickListener {
                onItemClickListener(book)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, view: Int): BooksViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BooksViewHolder(itemView, onItemClickListener)
    }


    override fun onBindViewHolder(viewHolder: BooksViewHolder, position: Int) {
        viewHolder.bindView(books[position])
    }


    // Retorna o tamanho da lista
    override fun getItemCount() = books.count()

}