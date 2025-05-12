package com.example.composestudy.di.manyStores

import androidx.lifecycle.ViewModel
import com.example.composestudy.di.ViewModelKey
import com.example.composestudy.presentation.manyStores.ManyStoresViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoMap

@Module
@DisableInstallInCheck
interface ManyStoresViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ManyStoresViewModel::class)
    fun bindsManyStoresViewModel(mainStoresViewModel: ManyStoresViewModel): ViewModel
}