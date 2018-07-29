package tk.andivinu.mvvmkotlin.network;

import io.reactivex.Observable
import tk.andivinu.mvvmkotlin.model.Search
import retrofit2.http.Body
import retrofit2.http.POST
import tk.andivinu.mvvmkotlin.model.SearchResult

/**
 * The interface which provides methods to get result of webservices
 */
interface SearchApi {
    /**
     * Get the list of the flights from the API
     */

    @POST("/search")
    fun getFlights(@Body search: Search): Observable<SearchResult>
}
