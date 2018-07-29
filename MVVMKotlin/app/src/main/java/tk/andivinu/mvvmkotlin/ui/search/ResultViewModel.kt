package tk.andivinu.mvvmkotlin.ui.search

import android.arch.lifecycle.MutableLiveData
import tk.andivinu.mvvmkotlin.base.BaseViewModel
import tk.andivinu.mvvmkotlin.model.Result

class ResultViewModel:BaseViewModel() {
    private val supplierName = MutableLiveData<String>()
    private val ticketClass = MutableLiveData<String>()
    private val duration = MutableLiveData<Int>()
    private val price = MutableLiveData<Double>()
    private val origin = MutableLiveData<String>()
    private val destination = MutableLiveData<String>()
    private val airline = MutableLiveData<String>()


    fun bind(searchResult: Result){
        supplierName.value = searchResult.supplierName
        ticketClass.value = searchResult.ticketClass
        duration.value = searchResult.duration
        price.value = searchResult.price

        for (item in searchResult.journey){
            origin.value= item.Origin
            destination.value= item.Destination
            airline.value=item.Airline

        }
    }

    fun getSupplierName():MutableLiveData<String>{
        return supplierName
    }

    fun getTicketClass():MutableLiveData<String>{
        return ticketClass
    }
    fun getAirline():MutableLiveData<String>{
        return airline
    }
    fun getDestination():MutableLiveData<String>{
        return destination
    }
    fun getOrigin():MutableLiveData<String>{
        return origin
    }
    fun getPrice():MutableLiveData<Double>{
        return price
    }
    fun getDuration():MutableLiveData<Int>{
        return duration
    }
}