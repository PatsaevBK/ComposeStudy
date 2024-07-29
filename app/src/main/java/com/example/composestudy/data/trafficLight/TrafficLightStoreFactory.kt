package com.example.composestudy.data.trafficLight

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.composestudy.data.trafficLight.TrafficLightStore.Intent
import com.example.composestudy.data.trafficLight.TrafficLightStore.Label
import com.example.composestudy.data.trafficLight.TrafficLightStore.State
import com.example.composestudy.data.trafficLight.TrafficLightStore.State.Colors.GREEN
import com.example.composestudy.data.trafficLight.TrafficLightStore.State.Colors.NONE
import com.example.composestudy.data.trafficLight.TrafficLightStore.State.Colors.RED
import com.example.composestudy.data.trafficLight.TrafficLightStore.State.Colors.YELLOW

internal class TrafficLightStoreFactory {

    fun create(
        storeFactory: StoreFactory,
    ): TrafficLightStore = object : TrafficLightStore,
        Store<Intent, State, Label> by storeFactory.create(
            name = "TrafficLightStore",
            initialState = State(
                colors = listOf(),
                selected = NONE
            ),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl(),
        ) {}

    sealed interface Action {
        data object Start : Action
    }

    sealed interface Message {
        data object Start : Message
        data class NextColor(val nextColor: State.Colors) : Message
    }

    private inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            dispatch(Action.Start)
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Message, Label>() {
        override fun executeAction(action: Action) {
            when (action) {
                Action.Start -> dispatch(Message.Start)
            }
        }

        override fun executeIntent(intent: Intent) {
            when(intent) {
                Intent.NextColor -> {
                    val nextColor = when (state().selected) {
                        RED -> YELLOW
                        YELLOW -> GREEN
                        GREEN -> RED
                        NONE -> RED
                    }
                    dispatch(Message.NextColor(nextColor))
                }
            }
        }
    }

    private class ReducerImpl : Reducer<State, Message> {
        override fun State.reduce(msg: Message): State {
            return when (msg) {
                Message.Start -> copy(selected = RED)
                is Message.NextColor -> copy(selected = msg.nextColor)
            }
        }

    }
}