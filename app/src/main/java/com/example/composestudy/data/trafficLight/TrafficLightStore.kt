package com.example.composestudy.data.trafficLight

import com.arkivanov.mvikotlin.core.store.Store
import com.example.composestudy.data.trafficLight.TrafficLightStore.Intent
import com.example.composestudy.data.trafficLight.TrafficLightStore.Label
import com.example.composestudy.data.trafficLight.TrafficLightStore.State

internal interface TrafficLightStore : Store<Intent, State, Label> {
    data class State(
        val colors: List<Colors>,
        val selected: Colors
    ) {
        enum class Colors {
            RED, YELLOW, GREEN, NONE
        }
    }


    sealed interface Intent {
        data object NextColor : Intent
    }

    sealed interface Label {
        data object Error : Label
    }
}