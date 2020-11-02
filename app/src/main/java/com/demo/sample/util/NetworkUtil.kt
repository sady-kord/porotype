package com.demo.sample.util

import android.net.ConnectivityManager
import android.net.NetworkInfo

object NetworkUtil {

    fun isOnline(connectivityManager: ConnectivityManager): Boolean {

        val mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        val mobileConnected = mobileInfo!!.state == NetworkInfo.State.CONNECTED

        val wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val wifiConnected = wifiInfo!!.state == NetworkInfo.State.CONNECTED

        return (mobileConnected || wifiConnected)
    }
}