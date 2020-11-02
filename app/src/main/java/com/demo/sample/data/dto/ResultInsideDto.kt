package com.demo.sample.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by S.kord on 11/1/2020.
 */
data class ResultInsideDto (

    @SerializedName("list_name")
    var list_name: String,

    @SerializedName("list_name_encoded")
    var list_name_encoded: String?,

    @SerializedName("display_name")
    var display_name: String?,

    @SerializedName("books")
    var books: List<BookDto>?
)