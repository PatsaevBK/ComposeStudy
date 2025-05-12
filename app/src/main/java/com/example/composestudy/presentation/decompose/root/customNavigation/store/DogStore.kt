package com.example.composestudy.presentation.decompose.root.customNavigation.store

import com.arkivanov.mvikotlin.core.store.Store
import com.example.composestudy.presentation.decompose.root.customNavigation.store.DogStore.Intent
import com.example.composestudy.presentation.decompose.root.customNavigation.store.DogStore.State

interface DogStore : Store<Intent, State, Nothing> {
    data class State(val count: Int) {
        companion object {
            val INITIAL = State(0)
        }
    }

    sealed interface Intent {
        data object Resume : Intent
        data object Pause : Intent
    }
}
