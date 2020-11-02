package com.demo.sample.network.repositories

import androidx.lifecycle.LiveData
import com.demo.sample.BuildConfig
import com.demo.sample.data.dto.*
import com.demo.sample.network.Resource
import com.demo.sample.network.service.BookService
import io.reactivex.Single
import javax.inject.Inject

class BookRepository @Inject
constructor(
    private var bookService: BookService
) {
    fun getResultAsTest(): Single<ApiResultDto> =
        bookService.getResultAsTest(BuildConfig.API_KEY)

    fun getResult(): LiveData<Resource<ApiResultDto>> =
        OnlineResource(bookService.getResult(BuildConfig.API_KEY)).asLiveData()

}