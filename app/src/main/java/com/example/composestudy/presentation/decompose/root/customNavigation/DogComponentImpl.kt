package com.example.composestudy.presentation.decompose.root.customNavigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.essenty.lifecycle.subscribe
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.composestudy.presentation.decompose.root.customNavigation.store.DogComponentStoreFactory
import com.example.composestudy.presentation.decompose.root.customNavigation.store.DogStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class DogComponentImpl(
    componentContext: ComponentContext,
    defaultStoreFactory: DefaultStoreFactory,
    scope: CoroutineScope,
    private val imageUrl: Images,
) : DogComponent, ComponentContext by componentContext {

    private val dogStore = instanceKeeper.getStore {
        DogComponentStoreFactory(defaultStoreFactory).create()
    }
    override val model: MutableValue<DogComponent.Model> = MutableValue(dogStore.state.toModel())

    init {
        lifecycle.subscribe(
            onResume = { dogStore.accept(DogStore.Intent.Resume) },
            onPause = { dogStore.accept(DogStore.Intent.Pause) },
            onDestroy = dogStore::dispose
        )

        scope.launch {
            dogStore.stateFlow.collect {
                model.value = it.toModel()
            }
        }
    }

    private fun DogStore.State.toModel(): DogComponent.Model {
        return DogComponent.Model(
            counter = count.toString(),
            imageUrl = imageUrl,
        )
    }

//    private class Holder(initialState: State): InstanceKeeper.Instance {
//        val state = MutableValue(initialState)
//
//        override fun onDestroy() {
//            Log.d("DogComponentImpl", "Holder onDestroy")
//        }
//
//        fun resume() {
//            Log.d("DogComponentImpl", "Holder resume")
//        }
//
//        fun pause() {
//            Log.d("DogComponentImpl", "Holder pause")
//        }
//    }
}