package com.demo.sample.mockwebtest

import com.google.gson.Gson
import com.demo.sample.network.MockResponseFileReader
import com.demo.sample.network.repositories.BookRepository
import com.demo.sample.network.service.BookService
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class MockApiUnitTests {

    private val server: MockWebServer = MockWebServer()
    private val MOCK_WEBSERVER_PORT = 8000

    private lateinit var placeholderApi: BookService
    private lateinit var jsonRepository: BookRepository

    @Before
    fun init() {
        server.start(MOCK_WEBSERVER_PORT)

        placeholderApi = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(BookService::class.java)

        jsonRepository = BookRepository(placeholderApi)
    }

    @After
    fun shutdown() {
        server.shutdown()
    }

    @Test
    fun `JsonPlaceholder APIs parse correctly`() {
        server.apply {
            enqueue(
                MockResponse().setBody(
                MockResponseFileReader(
                    "jsonplaceholder_success.json"
                ).content))
        }
        jsonRepository.getResultAsTest()
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertComplete()
            .assertValueCount(1)
            .assertValue { it.results.books?.size == 15 }
            .assertNoErrors()
    }
}