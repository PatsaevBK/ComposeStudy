package com.example.composestudy.di.manyStores

import androidx.lifecycle.ViewModel
import com.example.composestudy.di.ViewModelKey
import com.example.composestudy.presentation.manyStores.ManyStoresViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ManyStoresViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ManyStoresViewModel::class)
    fun bindsManyStoresViewModel(mainStoresViewModel: ManyStoresViewModel): ViewModel
}