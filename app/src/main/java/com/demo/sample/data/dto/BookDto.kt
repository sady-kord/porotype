package com.demo.sample.data.dto

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "books")
data class BookDto (

    @PrimaryKey
    @SerializedName("rank")
    var rank: Int?,

    @SerializedName("rank_last_week")
    var rank_last_week: Int?,

    @SerializedName("weeks_on_list")
    var weeks_on_list: Int?,

    @SerializedName("asterisk")
    var asterisk: Int? ,

    @SerializedName("dagger")
    var dagger: Int? ,

    @SerializedName("publisher")
    var publisher: String? ,

    @SerializedName("description")
    var description: String? ,

    @SerializedName("price")
    var price: Int? ,

    @SerializedName("title")
    var title: String? ,

    @SerializedName("author")
    var author: String? ,

    @SerializedName("contributor")
    var contributor: String? ,

    @SerializedName("book_image")
    var bookImage: String? ,

    @SerializedName("book_image_width")
    var book_image_width: Int? ,

    @SerializedName("book_image_height")
    var book_image_height: Int?

) : Parcelable