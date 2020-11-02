package com.demo.sample.di

import androidx.room.Room
import com.demo.sample.App
import com.demo.sample.dao.BookDao
import com.demo.sample.db.SampleDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDb(app: App): SampleDb {
        return Room
            .databaseBuilder(app, SampleDb::class.java, "cache.db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBookDao(db: SampleDb): BookDao {
        return db.bookDao()
    }

}
