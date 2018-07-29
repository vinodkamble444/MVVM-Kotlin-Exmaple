package tk.andivinu.mvvmkotlin.injection.module

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import tk.andivinu.mvvmkotlin.network.SearchApi
import tk.andivinu.mvvmkotlin.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import tk.andivinu.mvvmkotlin.model.SearchResult
import tk.andivinu.mvvmkotlin.utils.DefaultOnDataMismatchAdapter
import tk.andivinu.mvvmkotlin.utils.FilterNullValuesFromListAdapter
import java.util.*

/**
 * Module which provides all required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {
    /**
     * Provides the Search service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Search service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {

        var interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
         var client:OkHttpClient  =  OkHttpClient.Builder().addInterceptor(interceptor).build();
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder()
                        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                        .add(DefaultOnDataMismatchAdapter.newFactory(SearchResult::class.java, null))
                        .add(FilterNullValuesFromListAdapter.newFactory(SearchResult::class.java))
                        .build()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }
}