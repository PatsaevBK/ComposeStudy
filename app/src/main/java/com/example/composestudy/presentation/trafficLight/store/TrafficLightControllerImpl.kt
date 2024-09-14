package com.example.composestudy.presentation.trafficLight.store

import android.util.Log
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.doOnCreate
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.essenty.lifecycle.doOnPause
import com.arkivanov.essenty.lifecycle.doOnResume
import com.arkivanov.essenty.lifecycle.doOnStart
import com.arkivanov.essenty.lifecycle.doOnStop
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.states
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

internal class TrafficLightControllerImpl @Inject constructor(
    private val transformer: TrafficLightTransformer,
    val store: TrafficLightStore,
) : TrafficLightController {
    init {
        Log.d("XXX", "controller")
    }
    override fun onViewCreated(view: TrafficLightView, lifecycle: Lifecycle) {
        bind(lifecycle, BinderLifecycleMode.START_STOP, Dispatchers.Main.immediate) {
            Log.d("XXX", "Binder")
            view.events.mapNotNull {
                Log.d("XXX", "event = $it")
                transformer.convertToIntent(it)
            } bindTo store
            store.states.map {
                Log.d("XXX", "state = $it")
                transformer.convertToModel(it)
            } bindTo view
        }
        lifecycle.doOnDestroy {
            Log.d("XXX", "doOnDestroy")
//            a.stop()
        }
        lifecycle.doOnCreate {
//            a.start()
            Log.d("XXX", "doOnCreate")
        }
        lifecycle.doOnStart { Log.d("XXX", "doOnStart") }
        lifecycle.doOnStop { Log.d("XXX", "doOnStop") }
        lifecycle.doOnResume { Log.d("XXX", "doOnResume") }
        lifecycle.doOnPause { Log.d("XXX", "doOnPause") }
    }

    override fun doOnCleared() {
        store.dispose()
    }
}