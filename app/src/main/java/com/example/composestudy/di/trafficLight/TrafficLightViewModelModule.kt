package com.example.composestudy.di.trafficLight

import androidx.lifecycle.ViewModel
import com.example.composestudy.di.ViewModelKey
import com.example.composestudy.presentation.trafficLight.TrafficLightViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoMap

@Module
@DisableInstallInCheck
internal interface TrafficLightViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TrafficLightViewModel::class)
    fun bindTrafficLightViewModel(trafficLightViewModel: TrafficLightViewModel): ViewModel
}