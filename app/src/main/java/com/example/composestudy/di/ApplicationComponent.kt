package com.example.composestudy.di

import android.content.Context
import com.example.composestudy.di.decompose.DecomposeDaggerComponent
import com.example.composestudy.di.manyStores.ManyStoresComponent
import com.example.composestudy.di.trafficLight.TrafficLightComponent
import com.example.composestudy.presentation.ViewModelFactory
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [PresentationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun getTrafficLightComponentFactory(): TrafficLightComponent.Factory
    fun getManyStoresComponent(): ManyStoresComponent.Factory
    fun getViewModelFactory(): ViewModelFactory
    fun getDecomposeDaggerComponent(): DecomposeDaggerComponent.Factory

    @Component.Factory
    interface ApplicationComponentFactory {
        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}