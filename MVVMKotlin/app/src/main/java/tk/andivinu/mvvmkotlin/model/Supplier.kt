package tk.andivinu.mvvmkotlin.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Supplier(
        val id: Int,
        val name: String,
        val offerIndices: List<Int> = listOf()
)