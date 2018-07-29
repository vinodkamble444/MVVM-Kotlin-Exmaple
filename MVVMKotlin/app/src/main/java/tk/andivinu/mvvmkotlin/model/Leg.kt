package tk.andivinu.mvvmkotlin.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Leg(
        val id: Int,
        val airlineIndex: Int,
        val flightNumber: Int,
        val departure: Int,
        val arrival: Int,
        val destinationIndex: Int,
        val originIndex: Int

)
