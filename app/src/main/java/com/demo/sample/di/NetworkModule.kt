package com.demo.sample.di

import android.content.Context
import android.net.ConnectivityManager
import com.demo.sample.network.ConnectivityInterceptor
import com.demo.sample.App
import com.demo.sample.network.service.BookService
import com.demo.sample.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttp(connectivityInterceptor: ConnectivityInterceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.MINUTES)
            .readTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)

        httpClient.addInterceptor(connectivityInterceptor)

        return httpClient.build()
    }

    @Singleton
    @Provides
    fun provideConnectivityManager(app: App) =
        app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides
    @Singleton
    @Named("retrofit_base")
    fun provideRetrofitBase(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/books/v3/lists/current/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideRegisterService(@Named("retrofit_base") retrofit: Retrofit): BookService {
        return retrofit.create(BookService::class.java)
    }

}

