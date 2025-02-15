package com.example.composestudy.presentation.manyStores.store

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.example.composestudy.presentation.manyStores.store.ManyStoresStore.Intent
import com.example.composestudy.presentation.manyStores.store.ManyStoresStore.Label
import com.example.composestudy.presentation.manyStores.store.SharedManyStoresView.Event
import com.example.composestudy.presentation.manyStores.store.SharedManyStoresView.RouteEvent
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

internal class ManyStoresControllerImpl @Inject constructor(
    private val store: ManyStoresStore,
    private val viewModelTransformer: ManyStoresViewModelTransformer,
) : SharedManyStoresController {
//    override lateinit var routeEventCallback: (RouteEvent) -> Unit

    override fun onViewCreated(view: SharedManyStoresView, viewLifecycle: Lifecycle) {
        bind(viewLifecycle, BinderLifecycleMode.START_STOP) {
            view.events.mapNotNull(eventToIntent) bindTo store
            store.states.mapNotNull { viewModelTransformer.buildManyStoresViewModel(it) } bindTo view
//            store.labels.mapNotNull(labelToRouteEvent).bindTo(routeEventCallback)
        }
    }

    private val eventToIntent: (Event) -> Intent = { event ->
        when (event) {
            is Event.TappedOnModel -> Intent.TappedOnModel(event.id)
        }
    }

    private val labelToRouteEvent: (Label) -> RouteEvent = { label ->
        when (label) {
            is Label.OpenTrafficLight -> RouteEvent.OpenTrafficLight(label.id)
        }
    }
}