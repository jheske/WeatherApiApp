package com.png.interview.dagger.module

import android.app.Application

import com.png.interview.api.common_model.NetworkResponseAdapterFactory
import com.png.interview.dagger.scope.ApplicationScope
import com.png.interview.weather.api.model.AutocompleteResponseItem
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.Date
import javax.inject.Qualifier

@Module
class CommonApiModule {

    @Provides
    fun provideOkHttpClient(app: Application): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        return builder
    }

    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder =
        Retrofit.Builder().addCallAdapterFactory(NetworkResponseAdapterFactory())

    /**
     * I believe for Moshi to parse
     *      class AutocompleteResponse = ArrayList<AutocompleteResponseItem>()
     * I should add this custom adapter. Then add it to the provider below.
     * I don't think  @ToJson function should be necessary.
     *
     * For some reason, Moshi does not call the adapter.
     * I suspect there's some small step missing to connect this to the
     * rest of the network layer.
     */
    class ArrayListMoshiAdapter {
        @ToJson fun arrayListToJson(list: ArrayList<AutocompleteResponseItem>) : List<AutocompleteResponseItem> = list

        @FromJson fun arrayListFromJson(list: List<AutocompleteResponseItem>) : ArrayList<AutocompleteResponseItem> = ArrayList(list)
    }

    @Provides
    @ApplicationScope
    fun provideMoshi(): Moshi.Builder {
        return Moshi
            .Builder()
            .add(ArrayListMoshiAdapter())
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .add(KotlinJsonAdapterFactory())
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class MoshiNoDateAdapter
}
