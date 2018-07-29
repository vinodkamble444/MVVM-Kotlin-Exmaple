package tk.andivinu.mvvmkotlin.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import tk.andivinu.mvvmkotlin.ui.search.ResultListViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ResultListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}