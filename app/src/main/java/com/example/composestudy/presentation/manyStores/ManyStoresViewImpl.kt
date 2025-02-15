package com.example.composestudy.presentation.manyStores

import androidx.compose.runtime.mutableStateOf
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.example.composestudy.presentation.manyStores.store.SharedManyStoresView

class ManyStoresViewImpl :BaseMviView<SharedManyStoresView.Model, SharedManyStoresView.Event>(), SharedManyStoresView {
    private val _model = mutableStateOf(SharedManyStoresView.Model(emptyList()))
    val model = _model

    override fun render(model: SharedManyStoresView.Model) {
        _model.value = model
    }
}