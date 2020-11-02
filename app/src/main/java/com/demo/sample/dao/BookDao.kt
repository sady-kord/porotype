package com.demo.sample.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.demo.sample.data.dto.BookDto

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveBooks(bookList: List<BookDto>)

}