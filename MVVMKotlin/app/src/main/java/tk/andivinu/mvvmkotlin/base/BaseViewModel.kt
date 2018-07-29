package tk.andivinu.mvvmkotlin.base

import android.arch.lifecycle.ViewModel
import tk.andivinu.mvvmkotlin.injection.component.DaggerViewModelInjector
import tk.andivinu.mvvmkotlin.injection.component.ViewModelInjector
import tk.andivinu.mvvmkotlin.injection.module.NetworkModule
import tk.andivinu.mvvmkotlin.ui.search.ResultListViewModel

abstract class BaseViewModel:ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is ResultListViewModel -> injector.inject(this)
        }
    }
}