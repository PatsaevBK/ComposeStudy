package com.example.composestudy.presentation.decompose.root.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class DogComponentStoreFactory(
    private val defaultStoreFactory: StoreFactory,
) {

    fun create(): DogStore = object : DogStore,
        Store<DogStore.Intent, DogStore.State, Nothing> by defaultStoreFactory.create(
            name = "DogStore",
            initialState = DogStore.State.INITIAL,
            bootstrapper = coroutineBootstrapper {

            },
            executorFactory = ::Executor,
            reducer = {
                when (it) {
                    is Message.UpdateCount -> copy(count = it.count)
                }
            }
        ) { }

    private sealed interface Message {
        data class UpdateCount(val count: Int) : Message
    }

    private class Executor :
        CoroutineExecutor<DogStore.Intent, Nothing, DogStore.State, Message, Nothing>() {
        private var incrementJob: Job? = null

        override fun executeIntent(intent: DogStore.Intent) {
            when (intent) {
                DogStore.Intent.Resume -> {
                    incrementJob?.cancel()
                    incrementJob = scope.launch {
                        while (true) {
                            delay(1000)
                            dispatch(Message.UpdateCount(state().count + 1))
                        }
                    }
                }

                DogStore.Intent.Pause -> incrementJob?.cancel()
            }
        }
    }
}