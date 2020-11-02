package com.demo.sample.network

sealed class Resource<T> {
    fun handle(
        failureCallback: ((String, Int?) -> Unit)? = null,
        callback: ((T) -> Unit)?
    ) {

        when (this) {
            is Loading -> {
                data?.let { callback?.invoke(it ) }

            }
            is Failure -> {
                failureCallback?.invoke(message, code)

            }
            is Success -> {
                data?.let { callback?.invoke(it) }
            }
        }
    }
}

class Success<T>(val data: T?, val code: Int?) : Resource<T>()
class Failure<T>(val message: String, val data: T?, val code: Int?) : Resource<T>()
class Loading<T>(val data: T?) : Resource<T>()