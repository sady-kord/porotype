package com.demo.sample.network

import com.demo.sample.data.model.InternetError
import java.io.IOException

class NoConnectivityException : IOException() {
    override val message: String
        get() = InternetError.NoInternet.value
}