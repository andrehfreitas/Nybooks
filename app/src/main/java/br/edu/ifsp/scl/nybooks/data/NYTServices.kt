package br.edu.ifsp.scl.nybooks.data

import br.edu.ifsp.scl.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NYTServices {

    @GET("lists.json")
    fun getBooks(
        @Query("api-key") apiKey: String = "ntUHDyMyOgFSBofUHRM0QxAA3txVpZXv",
        @Query("list") list: String = "hardcover-fiction"
    ): Call<BookBodyResponse>
}