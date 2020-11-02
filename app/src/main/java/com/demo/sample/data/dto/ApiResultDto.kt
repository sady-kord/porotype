package com.demo.sample.data.dto

import com.google.gson.annotations.SerializedName

data class ApiResultDto(

    @SerializedName("status")
    var status: String,

    @SerializedName("num_results")
    var num_results: Int,

    @SerializedName("last_modified")
    var last_modified: String,

    @SerializedName("results")
    var results: ResultInsideDto
)