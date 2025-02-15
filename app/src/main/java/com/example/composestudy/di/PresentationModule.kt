package com.example.composestudy.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.composestudy.presentation.manyStores.store.ManyStoresControllerImpl
import com.example.composestudy.presentation.manyStores.store.ManyStoresStore
import com.example.composestudy.presentation.manyStores.store.ManyStoresStoreFactory
import com.example.composestudy.presentation.manyStores.store.ManyStoresViewModelTransformer
import com.example.composestudy.presentation.manyStores.store.ManyStoresViewModelTransformerImpl
import com.example.composestudy.presentation.manyStores.store.SharedManyStoresController
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
    fun bindsManyStoresTransformer(transformer: ManyStoresViewModelTransformerImpl): ManyStoresViewModelTransformer


    @Binds
    fun bindsTrafficLightController(trafficLightControllerImpl: TrafficLightControllerImpl): TrafficLightController

    @Binds
    fun bindsManyStoresController(manyStoresControllerImpl: ManyStoresControllerImpl): SharedManyStoresController
    companion object {
        @Provides
        fun providesStoreFactory(): StoreFactory = LoggingStoreFactory(DefaultStoreFactory())

        @Provides
        fun providesTrafficLightStore(trafficLightStoreFactory: TrafficLightStoreFactory): TrafficLightStore {
            return trafficLightStoreFactory.create()
        }

        @Provides
        fun providesManyStoresStore(manyStoresStoreFactory: ManyStoresStoreFactory): ManyStoresStore {
            return manyStoresStoreFactory.create()
        }
    }
}