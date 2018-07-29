package tk.andivinu.mvvmkotlin.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Offer(
        val id: Int,
        val price: Double,
        val flightIndex: Int,
        val ticketClassIndex: Int
)
