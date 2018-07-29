package tk.andivinu.mvvmkotlin.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Flight(
        val id: Int,
        val ticketClassIndex: Int,
        val segmentIndices: List<Int> = listOf()
)

