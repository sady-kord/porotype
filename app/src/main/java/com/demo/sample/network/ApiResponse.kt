package com.demo.sample.network

import retrofit2.Response
import timber.log.Timber
import java.util.regex.Pattern

@Suppress("unused") // T is used in extending classes
sealed class ApiResponse<T> {

    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(error.message ?: "unknown error",0)
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {

            return if (response.isSuccessful && null != response.body() ) {

                val body = response.body()

                if (body == null || response.code() == 204 ) {

                    ApiEmptyResponse()

                } else if (response.code() == 0){

                    ApiErrorResponse(response.message(),response.code())

                }

                else {

                    ApiSuccessResponse(
                        body = body,
                        linkHeader = response.headers()?.get("link"),
                        code = 0
                    )

                }


            } else {

                val message = "response.body()?.message"
                val errorMsg = if (message.isNullOrEmpty()) {
                    val msg = response.errorBody()?.string()
                    if (msg.isNullOrEmpty()) {
                        response.message()
                    } else {
                        msg
                    }
                } else {
                    message
                }
                ApiErrorResponse(errorMsg ?: "unknown error",0)
            }
        }
    }
}

class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(
    val body: T,
    val links: Map<String, String>,
    val code : Int?
) : ApiResponse<T>() {

    constructor(body: T, linkHeader: String?,code:Int?) : this(
        body = body,
        links = linkHeader?.extractLinks() ?: emptyMap(),
        code = code
    )

    val nextPage: Int? by lazy(LazyThreadSafetyMode.NONE) {
        links[NEXT_LINK]?.let { next ->
            val matcher = PAGE_PATTERN.matcher(next)
            if (!matcher.find() || matcher.groupCount() != 1) {
                null
            } else {
                try {
                    Integer.parseInt(matcher.group(1))
                } catch (ex: NumberFormatException) {
                    Timber.w("cannot parse next page from %s", next)
                    null
                }
            }
        }
    }

    companion object {
        private val LINK_PATTERN = Pattern.compile("<([^>]*)>[\\s]*;[\\s]*rel=\"([a-zA-Z0-9]+)\"")
        private val PAGE_PATTERN = Pattern.compile("\\bpage=(\\d+)")
        private const val NEXT_LINK = "next"

        private fun String.extractLinks(): Map<String, String> {
            val links = mutableMapOf<String, String>()
            val matcher = LINK_PATTERN.matcher(this)

            while (matcher.find()) {
                val count = matcher.groupCount()
                if (count == 2) {
                    links[matcher.group(2)] = matcher.group(1)
                }
            }
            return links
        }

    }
}

data class ApiErrorResponse<T>(val errorMessage: String,val code:Int) : ApiResponse<T>()
