package com.example.composestudy.presentation.decompose.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.composestudy.presentation.decompose.root.commonComponent.CommonComponent
import com.example.composestudy.presentation.decompose.root.customNavigation.CustomNavigationComponent

interface RootNavigationComponent {
    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {
        data class Common(val component: CommonComponent) : Child
        data class CustomNavigation(val component: CustomNavigationComponent): Child
    }
}