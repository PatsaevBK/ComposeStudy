package com.example.composestudy.di

import androidx.lifecycle.ViewModel
import com.example.composestudy.presentation.homeScreen.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoMap

@Module
@DisableInstallInCheck
internal interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindsHomeViewModel(homeViewModel: HomeViewModel): ViewModel
}