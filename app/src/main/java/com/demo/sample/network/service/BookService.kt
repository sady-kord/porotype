package com.demo.sample.network.service

import androidx.lifecycle.LiveData
import com.demo.sample.data.dto.*
import com.demo.sample.network.ApiResponse
import io.reactivex.Single
import retrofit2.http.*

interface BookService {

    @GET("hardcover-fiction.json")
    fun getResultAsTest(@Query("api-key") apiKey: String?): Single<ApiResultDto>

    @GET("hardcover-fiction.json")
    fun getResult(@Query("api-key") apiKey: String?): LiveData<ApiResponse<ApiResultDto>>

}
