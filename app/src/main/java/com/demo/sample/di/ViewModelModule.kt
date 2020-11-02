package com.demo.sample.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.example.github.di.ViewModelKey
import com.demo.sample.ui.home.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

}