package com.example.composestudy.presentation.manyStores

import androidx.lifecycle.ViewModel
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.example.composestudy.presentation.manyStores.store.SharedManyStoresController
import com.example.composestudy.presentation.manyStores.store.SharedManyStoresView
import javax.inject.Inject

class ManyStoresViewModel @Inject constructor(
    lifecycle: Lifecycle,
    private val manyStoresController: SharedManyStoresController,
): ViewModel() {
    val mviView = ManyStoresViewImpl()

    init {
        manyStoresController.onViewCreated(mviView, lifecycle)
    }

    fun nextColor(id: Int) {
        mviView.dispatch(SharedManyStoresView.Event.TappedOnModel(id))
    }
}