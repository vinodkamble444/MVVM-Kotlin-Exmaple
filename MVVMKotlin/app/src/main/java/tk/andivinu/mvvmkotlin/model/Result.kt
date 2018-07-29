package tk.andivinu.mvvmkotlin.model

data class Result(

        val supplierName: String,
        val price: Double,
        val ticketClass: String,
        val duration: Int,
        val journey: List<Journey> = listOf()

)

data class Journey(
        val  arrival : Int,
        val  departure : Int,
        val flightNumber : Int,
        val Origin : String,
        val Destination : String,
        val Airline : String
)