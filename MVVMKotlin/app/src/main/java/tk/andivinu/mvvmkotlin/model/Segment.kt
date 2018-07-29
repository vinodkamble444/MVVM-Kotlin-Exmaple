package tk.andivinu.mvvmkotlin.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Segment(
        val id: Int,
        val duration: Int,
        val legIndices: List<Int> = listOf()
)

