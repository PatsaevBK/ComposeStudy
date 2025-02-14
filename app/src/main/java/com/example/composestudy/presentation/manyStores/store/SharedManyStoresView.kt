package com.example.composestudy.presentation.manyStores.store

import com.arkivanov.mvikotlin.core.view.MviView
import com.example.composestudy.presentation.manyStores.store.SharedManyStoresView.Event
import com.example.composestudy.presentation.manyStores.store.SharedManyStoresView.Model
import com.example.composestudy.presentation.trafficLight.store.TrafficLightView

interface SharedManyStoresView : MviView<Model, Event> {
    data class Model(val  trafficLights: List<TrafficLightView.Model>)

    sealed interface Event {
        data class TappedOnModel(val id: Int): Event
    }
    sealed interface RouteEvent {
        data class OpenTrafficLight(val id: Int): RouteEvent
    }
}