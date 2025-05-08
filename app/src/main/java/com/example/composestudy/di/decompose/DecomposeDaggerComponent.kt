package com.example.composestudy.di.decompose

import com.arkivanov.decompose.ComponentContext
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
interface DecomposeDaggerComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance componentContext: ComponentContext): DecomposeDaggerComponent
    }
}