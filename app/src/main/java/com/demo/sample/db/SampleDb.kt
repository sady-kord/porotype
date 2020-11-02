package com.demo.sample.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.sample.dao.BookDao
import com.demo.sample.data.dto.BookDto

@Database(
    entities = [
        BookDto::class
    ]
    , version = 6, exportSchema = false
)

abstract class SampleDb : RoomDatabase() {

    abstract fun bookDao(): BookDao

}