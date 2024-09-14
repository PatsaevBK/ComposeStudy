package com.example.composestudy.presentation.trafficLight.store

internal interface TrafficLightTransformer {
    fun convertToModel(state: TrafficLightStore.State): TrafficLightView.Model
    fun convertToIntent(event: TrafficLightView.Event): TrafficLightStore.Intent
}