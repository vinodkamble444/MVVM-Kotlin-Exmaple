package tk.andivinu.mvvmkotlin.injection.component

import dagger.Component
import tk.andivinu.mvvmkotlin.injection.module.NetworkModule
import tk.andivinu.mvvmkotlin.ui.search.ResultListViewModel
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified PostListViewModel.
     * @param resultListViewModel ResultListViewModel in which to inject the dependencies
     */
    fun inject(resultListViewModel: ResultListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}