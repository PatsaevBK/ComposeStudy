package com.example.composestudy.navigation

internal sealed interface Screens {
    val description: String
    data object Home : Screens {
        override val description: String = ""
    }

    data object TrafficLights : Screens {
        override val description: String = "Sample app from ios developer course"
    }
}