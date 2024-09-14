package com.example.composestudy.presentation.trafficLight.store

import com.arkivanov.mvikotlin.core.view.MviView
import com.example.composestudy.presentation.trafficLight.store.TrafficLightView.Event
import com.example.composestudy.presentation.trafficLight.store.TrafficLightView.Model

interface TrafficLightView : MviView<Model, Event> {
    data class Model(
        val selectedColor: Pair<List<Colors>, Colors>
    ) {
        enum class Colors {
            RED, YELLOW, GREEN
        }
    }

    sealed interface Event {
        data object ColorChanged : Event
    }
}