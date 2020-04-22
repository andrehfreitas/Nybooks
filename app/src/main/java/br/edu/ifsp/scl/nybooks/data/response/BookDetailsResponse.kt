package br.edu.ifsp.scl.nybooks.data.response

import br.edu.ifsp.scl.nybooks.data.model.Book
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/*  Como name de Json é exatamente igual à propriedade não é necessário fazer a notação @Jason.
    Aqui foi feito por motivos didáticos */
@JsonClass(generateAdapter = true)
data class BookDetailsResponse (
    @Json(name = "title")
    val title: String,
    @Json(name = "author")
    val author: String,
    @Json (name = "description")
    val description: String
) {
    fun getBooksModel() = Book (
        title = this.title,
        author = this.author,
        description = this.description
    )
}

