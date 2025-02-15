package com.example.composestudy.presentation.manyStores.store

import com.arkivanov.essenty.lifecycle.Lifecycle


interface SharedManyStoresController {
//    var routeEventCallback: (SharedManyStoresView.RouteEvent) -> Unit

    fun onViewCreated(
        view: SharedManyStoresView,
        viewLifecycle: Lifecycle,
    )
}