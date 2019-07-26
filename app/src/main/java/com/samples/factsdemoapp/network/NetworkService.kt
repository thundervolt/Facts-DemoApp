package com.samples.factsdemoapp.network

import android.content.Context
import com.samples.factsdemoapp.data.model.FactsList
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Interface retrofit network service
 *
 * @author AkashG
 * @since 19-07-2019.
 */
interface NetworkService {

    @GET("facts.json")
    fun getFactsList(): Single<FactsList>

    companion object {
        val BASE_URL: String = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"

        fun getApiService(context: Context): NetworkService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkhttpCacheBuilder.getOkhttpCacheClient(context))
                .build()

            return retrofit.create(NetworkService::class.java)
        }
    }

}