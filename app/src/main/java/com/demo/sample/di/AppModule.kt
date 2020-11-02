package com.demo.sample.di

import dagger.Module

@Module(includes = [ViewModelModule::class, NetworkModule::class, DatabaseModule::class])
class AppModule