package com.example.composestudy.presentation.trafficLight

import android.util.Log
import androidx.lifecycle.ViewModel
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.example.composestudy.presentation.trafficLight.store.TrafficLightController
import com.example.composestudy.presentation.trafficLight.store.TrafficLightView
import javax.inject.Inject

internal class TrafficLightViewModel @Inject constructor(
    private val controller: TrafficLightController,
    lifecycle: Lifecycle,
): ViewModel() {
    val trafficLightViewImpl = TrafficLightViewImpl()

    init {
        controller.onViewCreated(trafficLightViewImpl, lifecycle)

    }

    fun nextColor() {
        Log.d("XXX", "nextColor")
        trafficLightViewImpl.dispatch(TrafficLightView.Event.ColorChanged)
    }

    override fun onCleared() {
        Log.d("XXX", "onCleared")
        controller.doOnCleared()
    }
}