package com.example.composestudy.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.composestudy.presentation.trafficLight.store.TrafficLightController
import com.example.composestudy.presentation.trafficLight.store.TrafficLightControllerImpl
import com.example.composestudy.presentation.trafficLight.store.TrafficLightStore
import com.example.composestudy.presentation.trafficLight.store.TrafficLightStoreFactory
import com.example.composestudy.presentation.trafficLight.store.TrafficLightTransformer
import com.example.composestudy.presentation.trafficLight.store.TrafficLightTransformerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface PresentationModule {

    @Binds
    fun bindsTransformer(transformer: TrafficLightTransformerImpl): TrafficLightTransformer

    @Binds
    fun bindsTrafficLightController(trafficLightControllerImpl: TrafficLightControllerImpl): TrafficLightController

    companion object {
        @Provides
        fun providesStoreFactory(): StoreFactory = LoggingStoreFactory(DefaultStoreFactory())

        @Provides
        fun providesTrafficLightStore(trafficLightStoreFactory: TrafficLightStoreFactory): TrafficLightStore {
            return trafficLightStoreFactory.create()
        }
    }
}