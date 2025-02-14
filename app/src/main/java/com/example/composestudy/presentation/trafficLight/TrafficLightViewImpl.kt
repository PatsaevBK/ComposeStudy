package com.example.composestudy.presentation.trafficLight

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.example.composestudy.presentation.trafficLight.store.TrafficLightView
import com.example.composestudy.presentation.trafficLight.store.TrafficLightView.Event
import com.example.composestudy.presentation.trafficLight.store.TrafficLightView.Model

internal class TrafficLightViewImpl: BaseMviView<Model, Event>(), TrafficLightView {
    private val _model = mutableStateOf(Model(0, Pair(emptyList(), Model.Colors.YELLOW)))
    val model: State<Model> = _model
    override fun render(model: Model) {
        Log.d("XXX", "render $model")
        _model.value = model
    }

}