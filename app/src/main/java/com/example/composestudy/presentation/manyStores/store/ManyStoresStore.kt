package com.example.composestudy.presentation.manyStores.store

import com.arkivanov.mvikotlin.core.store.Store
import com.example.composestudy.presentation.manyStores.store.ManyStoresStore.Intent
import com.example.composestudy.presentation.manyStores.store.ManyStoresStore.Label
import com.example.composestudy.presentation.manyStores.store.ManyStoresStore.State
import com.example.composestudy.presentation.trafficLight.store.TrafficLightStore

internal interface ManyStoresStore : Store<Intent, State, Label> {
    data class State(val subStores: Map<Int, TrafficLightStore.State>)
    sealed interface Intent {
        data class TappedOnModel(val id: Int) : Intent
        data class TappedNextColor(val id: Int) : Intent
    }
    sealed interface Label {
        data class OpenTrafficLight(val id: Int) : Label
    }
}
