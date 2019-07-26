package com.samples.factsdemoapp.network

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient

/**
 * Okhttp client cache builder
 *
 * @author AkashG
 * @since 26/07/19.
 */
object OkhttpCacheBuilder {

    fun getOkhttpCacheClient(context: Context): OkHttpClient {

        val cacheSize = (5 * 1024 * 1024).toLong()  // 5MB
        val mCache = Cache(context.cacheDir, cacheSize)

        return OkHttpClient.Builder()
            .cache(mCache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (NetworkConnectivityManager.hasNetwork(context)!!)
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                chain.proceed(request)
            }
            .build()
    }

}