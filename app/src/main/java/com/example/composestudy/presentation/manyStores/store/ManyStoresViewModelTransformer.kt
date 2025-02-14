package com.example.composestudy.presentation.manyStores.store

internal interface ManyStoresViewModelTransformer {
    fun buildManyStoresViewModel(state: ManyStoresStore.State): SharedManyStoresView.Model
}