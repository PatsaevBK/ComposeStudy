package com.example.composestudy.navigation

import kotlinx.serialization.Serializable

internal sealed interface Screens {
    val description: String

    @Serializable
    data object Home : Screens {
        override val description: String = ""
    }

    @Serializable
    data object TrafficLights : Screens {
        override val description: String = "Sample app from ios developer course"
    }

    @Serializable
    data object ManyStores : Screens {
        override val description: String = "Try to use more than one store for screen"
    }

    @Serializable
    data object Decompose : Screens {
        override val description: String
            get() = "Understand custom decompose navigation"
    }
}