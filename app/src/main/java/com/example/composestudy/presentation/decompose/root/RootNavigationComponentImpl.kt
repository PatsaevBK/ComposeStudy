package com.example.composestudy.presentation.decompose.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.example.composestudy.presentation.decompose.root.commonComponent.CommonComponentImpl
import com.example.composestudy.presentation.decompose.root.customNavigation.CustomNavigationComponentImpl
import kotlinx.serialization.Serializable

class RootNavigationComponentImpl(componentContext: ComponentContext, private val defaultStoreFactory: DefaultStoreFactory) : RootNavigationComponent, ComponentContext by componentContext {
    private val nav = StackNavigation<Config>()
    private val _stack = childStack(
        source = nav,
        serializer = Config.serializer(),
        initialConfiguration = Config.Common,
        key = "RootChildStack",
        handleBackButton = true,
        childFactory = ::child
    )

    override val stack: Value<ChildStack<*, RootNavigationComponent.Child>> = _stack

    @OptIn(DelicateDecomposeApi::class)
    private fun child(config: Config, ctx: ComponentContext): RootNavigationComponent.Child {
        return when (config) {
            Config.CustomNavigation -> {
                val component = CustomNavigationComponentImpl(
                    componentContext = ctx,
                    defaultStoreFactory = defaultStoreFactory,
                    onBack = nav::pop
                )
                RootNavigationComponent.Child.CustomNavigation(component = component)
            }

            Config.Common -> RootNavigationComponent.Child.Common(
                component = CommonComponentImpl(
                    children = RootNavigationComponent.Child::class.sealedSubclasses
                ) { child ->
                    if (child in RootNavigationComponent.Child::class.sealedSubclasses.map { it.simpleName }) {
                        if (child == RootNavigationComponent.Child.CustomNavigation::class.simpleName) {
                            nav.push(Config.CustomNavigation)
                        }
                    }
                }
            )
        }
    }

    @Serializable
    private sealed interface Config {
        data object Common: Config
        data object CustomNavigation : Config
    }
}