package tk.andivinu.mvvmkotlin.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Search(
        val origin: String,
        val destination: String,
        val departureDate: Long,
        val returnDate: Long ,
        val ticketCount: Int


)