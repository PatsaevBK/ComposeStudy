package com.example.composestudy.presentation.manyStores.store

import com.example.composestudy.presentation.manyStores.store.SharedManyStoresView.Model
import com.example.composestudy.presentation.trafficLight.store.TrafficLightTransformer
import javax.inject.Inject

internal class ManyStoresViewModelTransformerImpl @Inject constructor(
    private val transformer: TrafficLightTransformer,
) : ManyStoresViewModelTransformer {
    override fun buildManyStoresViewModel(state: ManyStoresStore.State): Model {
        val models = state.subStores.values.map { transformer.convertToModel(it) }
        return Model(models)
    }
}
