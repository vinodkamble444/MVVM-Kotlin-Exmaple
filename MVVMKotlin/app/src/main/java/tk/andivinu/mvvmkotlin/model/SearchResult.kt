package tk.andivinu.mvvmkotlin.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResult(
        val originName: String,
        val originCode: String,
        val destinationName: String,
        val destinationCode: String,
        val airlines: List<Airline> = listOf(),
         val airports: List<Airport> = listOf(),
         val ticketClasses: List<TicketClass> = listOf(),
         val flights: List<Flight> = listOf(),
         val legs: List<Leg> = listOf(),
         val segments: List<Segment> = listOf(),
         val suppliers: List<Supplier> = listOf(),
         val offers: List<Offer> = listOf()
       /* val id: String,
        val title: String,
        @Json(name = "cell_image") val image: Image,
        val author: User,
        val video: Video? = null,
        val subtitle: String = "",
        val content: List<Content> = listOf(),
        @Json(name = "like_count") val likeCount: Int = 0,
        @Json(name = "published_at") val publishedAt: Date = Date(0)*/
)