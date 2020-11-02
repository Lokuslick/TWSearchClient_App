package com.example.twsearchclient.injection.component


import com.example.twsearchclient.injection.module.NetworkModule
import com.example.twsearchclient.viewModel.TwitterClientViewModel
import com.example.twsearchclient.viewModel.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class),(ViewModelModule::class)])
interface ViewModelInjector {

    fun inject(twitterClientViewModel: TwitterClientViewModel)


    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder

    }
}