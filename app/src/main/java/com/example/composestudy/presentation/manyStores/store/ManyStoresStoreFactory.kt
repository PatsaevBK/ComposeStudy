package com.example.composestudy.presentation.manyStores.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.example.composestudy.presentation.manyStores.store.ManyStoresStore.Intent
import com.example.composestudy.presentation.manyStores.store.ManyStoresStore.Label
import com.example.composestudy.presentation.manyStores.store.ManyStoresStore.State
import com.example.composestudy.presentation.trafficLight.store.TrafficLightStore
import com.example.composestudy.presentation.trafficLight.store.TrafficLightStoreFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class ManyStoresStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val trafficLightStoreFactory: TrafficLightStoreFactory,
) {
    fun create(
        stores: List<TrafficLightStore> = (1..30).map {
            trafficLightStoreFactory.create(it)
        }
    ): ManyStoresStore =
        object :
            ManyStoresStore,
            Store<Intent, State, Label> by storeFactory.create(
                name = "ManyStoresStore",
                initialState = State(emptyMap()),
                bootstrapper = BootstrapperImpl(stores),
                executorFactory = { ExecutorImpl(stores) },
                reducer = ReducerImpl,
            ) {}

    private sealed interface Action {
        data class SubStoresUpdate(val state: TrafficLightStore.State) : Action
    }

    private sealed interface Message {
        data class SubStoresUpdate(val subState: TrafficLightStore.State) : Message
    }

    private class BootstrapperImpl(
        val stores: List<TrafficLightStore>,
    ) : CoroutineBootstrapper<Action>() {
        override fun invoke() {

            stores.forEach {
                scope.launch {
                    it.states.collect {
                        dispatch(Action.SubStoresUpdate(it))
                    }
                }
            }
        }
    }

    private inner class ExecutorImpl(private val stores: List<TrafficLightStore>) :
        CoroutineExecutor<Intent, Action, State, Message, Label>() {
        override fun executeAction(action: Action) {
            when (action) {
                is Action.SubStoresUpdate -> {
                    dispatch(Message.SubStoresUpdate(action.state))
                }
            }
        }

        override fun executeIntent(intent: Intent) {
            when (intent) {
                is Intent.TappedOnModel -> {
                    publish(Label.OpenTrafficLight(intent.id))
                }

                is Intent.TappedNextColor -> {
                    stores[intent.id].accept(TrafficLightStore.Intent.NextColor)
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Message> {
        override fun State.reduce(msg: Message): State {
            return when (msg) {
                is Message.SubStoresUpdate -> {
                    val entry = msg.subState.id to msg.subState
                    val new: Map<Int, TrafficLightStore.State> = subStores + entry
                    copy(subStores = new)
                }
            }
        }
    }
}