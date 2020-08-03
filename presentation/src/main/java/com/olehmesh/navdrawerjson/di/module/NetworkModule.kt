package com.olehmesh.navdrawerjson.di.module

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.olehmesh.navdrawerjson.di.scope.ApiScope
import com.olehmesh.repository.BuildConfig
import com.olehmesh.repository.network.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    @ApiScope
    @Provides
    fun networkService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @ApiScope
    @Provides
    fun retrofit(builder: Retrofit.Builder): Retrofit {
        return builder.build()
    }

    @ApiScope
    @Provides
    fun retrofitBuilder(): Retrofit.Builder {

        val okHttpClient = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE

            okHttpClient.addInterceptor(interceptor)

        }

        return Retrofit.Builder()

            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient.build())

    }

    companion object {
        private const val BASE_URL = "https://www.dropbox.com/"
    }

}