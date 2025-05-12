package com.example.composestudy.presentation.decompose.root

import com.arkivanov.decompose.Child
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.children.ChildNavState
import com.arkivanov.decompose.router.children.NavState
import com.arkivanov.decompose.router.children.SimpleChildNavState
import com.arkivanov.decompose.router.children.SimpleNavigation
import com.arkivanov.decompose.router.children.children
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.composestudy.presentation.decompose.Images
import com.example.composestudy.presentation.decompose.root.RootComponent.Children
import com.example.composestudy.presentation.decompose.root.RootComponent.Mode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.Serializable

class RootComponentImpl(
    componentContext: ComponentContext,
    defaultStoreFactory: DefaultStoreFactory,
) : RootComponent, ComponentContext by componentContext {

    private val nav = SimpleNavigation<(NavigationState) -> NavigationState>()
    private val scope = CoroutineScope(Dispatchers.Main.immediate)

    private val _children: Value<Children<Config, DogComponent>> = children(
        source = nav,
        stateSerializer = NavigationState.serializer(),
        key = "carousel",
        initialState = {
            NavigationState(
                configurations = Images.entries.map(::Config),
                index = 0,
                mode = Mode.CAROUSEL
            )
        },
        navTransformer = { state, transformer -> transformer(state) },
        stateMapper = { state, children ->
            Children(
                items = children.map { it as Child.Created },
                index = state.index,
                mode = state.mode,
            )
        },
        backTransformer = {
            it.takeIf { it.index > 0 }?.let { state ->
                { state.copy(index = state.index - 1) }
            }
        },
        childFactory = { config, componentContext ->
            DogComponentImpl(
                componentContext = componentContext,
                imageUrl = config.imageUrl,
                scope = scope,
                defaultStoreFactory = defaultStoreFactory,
            )
        }
    )

    override val children: Value<Children<*, DogComponent>> = _children

    @Serializable
    private data class Config(val imageUrl: Images)

    @Serializable
    private data class NavigationState(
        val configurations: List<Config>,
        val index: Int,
        val mode: Mode
    ) : NavState<Config> {

        override val children: List<ChildNavState<Config>> by lazy {
            configurations.mapIndexed { index, config ->
                SimpleChildNavState(
                    configuration = config,
                    status = if (index == this.index) ChildNavState.Status.RESUMED else ChildNavState.Status.CREATED
                )
            }
        }
    }
}