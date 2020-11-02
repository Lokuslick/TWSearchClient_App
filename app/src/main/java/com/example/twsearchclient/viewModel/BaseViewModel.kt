package com.example.twsearchclient.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.twsearchclient.injection.component.DaggerViewModelInjector
import com.example.twsearchclient.injection.component.ViewModelInjector
import com.example.twsearchclient.injection.module.NetworkModule

abstract class BaseViewModel : ViewModel(){

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is TwitterClientViewModel -> injector.inject(this)
        }
    }


}
