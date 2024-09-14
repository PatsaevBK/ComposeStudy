package com.example.composestudy.presentation.trafficLight.store

import com.arkivanov.essenty.lifecycle.Lifecycle

interface TrafficLightController {
    fun onViewCreated(view: TrafficLightView, lifecycle: Lifecycle)
    fun doOnCleared()
}