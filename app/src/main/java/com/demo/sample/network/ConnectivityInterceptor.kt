package com.demo.sample.network

import android.net.ConnectivityManager
import com.demo.sample.App
import com.demo.sample.util.NetworkUtil.isOnline
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class ConnectivityInterceptor @Inject constructor(
   private val connectivityManager : ConnectivityManager ,
   val app : App
) : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {

        if (!isOnline(connectivityManager)) {
            throw NoConnectivityException()
        }

        synchronized(this) {

            val originalRequest = chain.request()
            val authenticationRequest = request(originalRequest)
            val initialResponse = chain.proceed(authenticationRequest)

            return initialResponse
        }

    }

    private fun request(originalRequest: Request): Request {

        return originalRequest.newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Content-type", "application/json")
            .addHeader("MNX-Client", "AndroidApp")
            .build()
    }
}