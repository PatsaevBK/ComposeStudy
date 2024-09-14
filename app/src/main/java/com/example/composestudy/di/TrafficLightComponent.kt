package com.example.composestudy.di

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.example.composestudy.presentation.ViewModelFactory
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [TrafficLightViewModelModule::class])
interface TrafficLightComponent {

    fun getViewModelFactory(): ViewModelFactory

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance lifecycle: Lifecycle
        ): TrafficLightComponent
    }
}