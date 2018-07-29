package tk.andivinu.mvvmkotlin.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Airport(
        val id: Int,
        val name: String
)
