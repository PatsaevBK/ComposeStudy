package com.example.composestudy.di

import androidx.lifecycle.ViewModel
import com.example.composestudy.presentation.trafficLight.TrafficLightViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface TrafficLightViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TrafficLightViewModel::class)
    fun bindTrafficLightViewModel(trafficLightViewModel: TrafficLightViewModel): ViewModel
}