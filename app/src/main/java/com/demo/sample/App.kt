package com.demo.sample

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

open class App : Application() , HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var appExecutors: AppExecutors

    override fun activityInjector() = activityInjector

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)

    }

}