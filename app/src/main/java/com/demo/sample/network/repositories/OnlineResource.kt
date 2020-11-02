package com.demo.sample.network.repositories

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.demo.sample.network.*

open class OnlineResource<RequestType>(
    private val apiResponse: LiveData<ApiResponse<RequestType>>
) {

    private val result = MediatorLiveData<Resource<RequestType>>()

    init {
        result.value = Loading(null)
        fetchFromNetwork(MutableLiveData())
    }

    @MainThread
    private fun setValue(newValue: Resource<RequestType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork(emptySource: LiveData<RequestType>) {
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
//            result.removeSource(emptySource)
            when (response) {
                is ApiSuccessResponse -> {
                    setValue(Success(response.body , response.code))
                }
                is ApiEmptyResponse -> {
                    setValue(Success(null , null))
                }
                is ApiErrorResponse -> {
                    onFetchFailed()
                    setValue(
                        Failure(
                            response.errorMessage,
                            null,response.code
                        )
                    )
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<RequestType>>
}
