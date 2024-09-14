package com.example.composestudy.presentation.trafficLight.store
import android.util.Log
import com.example.composestudy.presentation.trafficLight.store.TrafficLightStore.State
import com.example.composestudy.presentation.trafficLight.store.TrafficLightView.Model.Colors
import javax.inject.Inject

internal class TrafficLightTransformerImpl @Inject constructor(): TrafficLightTransformer {

    init {
        Log.d("XXX", "TrafficLightTransformerImpl")
    }

    override fun convertToModel(state: State): TrafficLightView.Model = state.toModel()
    override fun convertToIntent(event: TrafficLightView.Event): TrafficLightStore.Intent =
        when (event) {
            TrafficLightView.Event.ColorChanged -> TrafficLightStore.Intent.NextColor
        }


    private fun State.toModel(): TrafficLightView.Model = TrafficLightView.Model(
        Pair(colors.toModel(), selected.selected())
    )

    private fun List<State.Colors>.toModel() = map {
        when (it) {
            State.Colors.RED -> Colors.RED
            State.Colors.YELLOW -> Colors.YELLOW
            State.Colors.GREEN -> Colors.GREEN
        }
    }

    private fun State.Colors.selected(): Colors = when (this) {
        State.Colors.RED -> Colors.RED
        State.Colors.YELLOW -> Colors.YELLOW
        State.Colors.GREEN -> Colors.GREEN
    }
}