package tk.andivinu.mvvmkotlin.ui.search

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableInt
import android.util.Log
import android.view.View
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import tk.andivinu.mvvmkotlin.R
import tk.andivinu.mvvmkotlin.base.BaseViewModel
import tk.andivinu.mvvmkotlin.model.*
import tk.andivinu.mvvmkotlin.network.SearchApi
import javax.inject.Inject


class ResultListViewModel() : BaseViewModel() {
    @Inject
    lateinit var postApi: SearchApi
    val postListAdapter: ResultListAdapter = ResultListAdapter()
    var searchButtonVisibility: ObservableInt
    var resultList = ArrayList<Result>()

    init {
        searchButtonVisibility = ObservableInt(View.VISIBLE)
    }

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

    private lateinit var subscription: Disposable

    init {
        loadPosts()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadPosts() {
        subscription =
                postApi.getFlights(search = Search("CPH", "PAR", 1958599800000, 10598599800000, 1))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { onRetrievePostListStart() }
                        .doOnTerminate { onRetrievePostListFinish() }
                        .subscribe(
                                { result -> onRetrievePostListSuccess(result) },
                                { onRetrievePostListError() }
                        )
    }

    private fun onRetrievePostListStart() {
        Log.d("TAG", "onRetrievePostListStart")
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(searchResult: SearchResult) {
        Log.d("TAG", "onRetrievePostListSuccess " + searchResult.toString())
        /*resultList!!.suppliers.forEach { n ->
            Log.d("VINOD", "----------Supplier Name -------------------: " + n.name)

            n.offerIndices.forEach { i ->
                Log.d("VINOD", "#######################################start######################################")
                Log.d("VINOD", "Price : " + resultList!!.offers[i].price)
                Log.d("VINOD", "Ticket Class : " + resultList!!.ticketClasses[resultList!!.offers[i].ticketClassIndex].name)
                //Log.d("VINOD", "Ticket Class : ")
                resultList!!.flights[resultList!!.offers[i].flightIndex].segmentIndices.forEach { j ->
                    Log.d("VINOD", "*******************************start******************************************")
                    Log.d("VINOD", "------------Duration--------- : " + resultList!!.segments[j].duration)
                    Log.d("VINOD", "Connected Filght"+(resultList!!.segments[j].legIndices.size))
                    Log.d("VINOD","Stops"+(resultList!!.segments[j].legIndices.size-1))
                    var journeyList=ArrayList<Journey>()
                    resultList!!.segments[j].legIndices.forEach { k ->

                        Log.d("VINOD", "------------------------start--------------------------------------")
                        Log.d("VINOD", "arrival : " + resultList!!.legs[k].arrival)
                        Log.d("VINOD", "departure : " + resultList!!.legs[k].departure)
                        Log.d("VINOD", "flightNumber : " + resultList!!.legs[k].flightNumber)
                        Log.d("VINOD", "Origin : " +  resultList!!.airports[resultList!!.legs[k].originIndex].name)
                        Log.d("VINOD", "Destination : " + resultList!!.airports[resultList!!.legs[k].destinationIndex].name)
                        Log.d("VINOD", "Airline : " + resultList!!.airlines[resultList!!.legs[k].airlineIndex].name)
                        Log.d("VINOD", "------------------------End--------------------------------------")
                        journeyList.add(Journey(resultList!!.legs[k].arrival,resultList!!.legs[k].departure,resultList!!.legs[k].flightNumber,resultList!!.airports[resultList!!.legs[k].originIndex].name,resultList!!.airports[resultList!!.legs[k].destinationIndex].name,resultList!!.airlines[resultList!!.legs[k].airlineIndex].name))

                    }
                   // resultList.add(Result(n.name,resultList!!.offers[i].price,resultList!!.ticketClasses[resultList!!.offers[i].ticketClassIndex].name,resultList!!.segments[i].duration,journeyList))
                    Log.d("VINOD", "*******************************End******************************************")
                }
                Log.d("VINOD", "#######################################End###################################### :")

            }
        }*/
        Observable.create(ObservableOnSubscribe<SearchResult>
        { emitter ->
            try {

                emitter.onNext(searchResult)
                emitter.onComplete()
            } catch (error: Exception) {
                emitter.onError(error)
            }
        })
                .map { result ->
                    try {
                        Log.d("TAG", "completed" + result)
                        processResult(result)
                    } catch (error: Exception) {
                        Log.d("TAG", "Error " + error.localizedMessage)
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> displayResult(result) },
                        { error -> Log.e("TAG", "{$error.message}") },
                        { Log.d("TAG", "completed") }
                )

    }

    private fun displayResult(result: Any) {
        postListAdapter.updatePostList(resultList)
    }


    private fun processResult(article: SearchResult) {
        article!!.suppliers.forEach { n ->
            n.offerIndices.forEach { i ->
                article!!.flights[article!!.offers[i].flightIndex].segmentIndices.forEach { j ->
                    var journeyList = ArrayList<Journey>()
                    article!!.segments[j].legIndices.forEach { k ->
                        journeyList.add(Journey(article!!.legs[k].arrival, article!!.legs[k].departure, article!!.legs[k].flightNumber, article!!.airports[article!!.legs[k].originIndex].name, article!!.airports[article!!.legs[k].destinationIndex].name, article!!.airlines[article!!.legs[k].airlineIndex].name))
                    }
                    resultList.add(Result(n.name, article!!.offers[i].price, article!!.ticketClasses[article!!.offers[i].ticketClassIndex].name, article!!.segments[j].duration, journeyList))
                }
            }
        }
    }

    private fun onRetrievePostListError() {
        Log.d("TAG", "onRetrievePostListError")
        errorMessage.value = R.string.post_error
    }

    fun onClickSearch(view: View) {
        var search = Search("CPH", "PAR", 1470434400000, 1471125600000, 1)
    }
}