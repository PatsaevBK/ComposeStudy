package com.example.composestudy.presentation.decompose.root.customNavigation

import android.util.Log
import com.arkivanov.decompose.Child
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.children.ChildNavState
import com.arkivanov.decompose.router.children.NavState
import com.arkivanov.decompose.router.children.SimpleChildNavState
import com.arkivanov.decompose.router.children.SimpleNavigation
import com.arkivanov.decompose.router.children.children
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.composestudy.presentation.decompose.root.customNavigation.CustomNavigationComponent.Children
import com.example.composestudy.presentation.decompose.root.customNavigation.CustomNavigationComponent.Mode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.Serializable

class CustomNavigationComponentImpl(
    componentContext: ComponentContext,
    defaultStoreFactory: DefaultStoreFactory,
    private val onBack: () -> Unit,
) : CustomNavigationComponent, ComponentContext by componentContext {

    private val nav = SimpleNavigation<(NavigationState) -> NavigationState>()
    private val scope = CoroutineScope(Dispatchers.Main.immediate)

    private val _children: Value<Children<Config, DogComponent>> = children(
        source = nav,
        stateSerializer = NavigationState.serializer(),
        key = "carousel",
        initialState = {
            NavigationState(
                configurations = Images.entries.map(CustomNavigationComponentImpl::Config),
                index = 0,
                mode = Mode.CAROUSEL
            )
        },
        navTransformer = { state, transformer ->
            Log.d("XXX","navTransformer { state = $state, transformer = $transformer}")
            transformer(state)
        },
        stateMapper = { state, children ->
            Log.d("XXX","stateMapper { state = $state, children = $children }")
            Children(
                items = children.map { it as Child.Created },
                index = state.index,
                mode = state.mode,
            )
        },
//        backTransformer = {
//            it.takeIf { it.index > 0 }?.let { state ->
//                { state.copy(index = state.index - 1) }
//            }
//        },
        childFactory = { config, componentContext ->
            Log.d("XXX","childFactory { config = $config, componentContext = $componentContext }")
            DogComponentImpl(
                componentContext = componentContext,
                imageUrl = config.imageUrl,
                scope = scope,
                defaultStoreFactory = defaultStoreFactory,
            )
        }
    )

    override val children: Value<Children<*, DogComponent>> = _children

    override fun onForwardClick() {
        nav.navigate { navigationState ->
            navigationState.copy(index = (navigationState.index + 1) % navigationState.configurations.size)
        }
    }

    override fun onBackwardClick() {
        nav.navigate { navigationState ->
            val size = navigationState.configurations.size
            navigationState.copy(index = (size + navigationState.index - 1) % size)
        }
    }

    override fun remove() {
        nav.navigate { navigationState ->
            val configs = navigationState.configurations.dropLast(1)
            navigationState.copy(configurations = configs)
        }
    }

    override fun onBack() {
        onBack.invoke()
    }

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