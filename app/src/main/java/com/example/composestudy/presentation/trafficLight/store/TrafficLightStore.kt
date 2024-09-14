package com.example.composestudy.presentation.trafficLight.store

import com.arkivanov.mvikotlin.core.store.Store
import com.example.composestudy.presentation.trafficLight.store.TrafficLightStore.Intent
import com.example.composestudy.presentation.trafficLight.store.TrafficLightStore.Label
import com.example.composestudy.presentation.trafficLight.store.TrafficLightStore.State

internal interface TrafficLightStore : Store<Intent, State, Label> {
    data class State(
        val colors: List<Colors>,
        val selected: Colors
    ) {
        enum class Colors {
            RED, YELLOW, GREEN
        }
    }

    sealed interface Intent {
        data object NextColor : Intent
    }

    sealed interface Label {
        data object Error : Label
    }
}