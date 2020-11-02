package com.example.twsearchclient.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.kotlininstitute.injection.ViewModelFactory
import com.example.twsearchclient.injection.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TwitterClientViewModel::class)
    internal abstract fun bindTwitterClientViewModel(twitterClientViewModel: TwitterClientViewModel): ViewModel


    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
