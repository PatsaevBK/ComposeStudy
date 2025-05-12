package com.example.composestudy.presentation.decompose.root.commonComponent

import com.example.composestudy.presentation.decompose.root.RootNavigationComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.reflect.KClass

class CommonComponentImpl(
    children: List<KClass<out RootNavigationComponent.Child>>,
    private val onButtonClick: (String) -> Unit
) : CommonComponent {

    private val _model = MutableStateFlow(children.toModel())
    override val model: StateFlow<CommonComponent.Model> = _model

    override fun navigateTo(child: String) {
        onButtonClick.invoke(child)
    }
}

private fun List<KClass<out RootNavigationComponent.Child>>.toModel(): CommonComponent.Model =
    CommonComponent.Model(buttons = this.map { it.simpleName ?: "Unknown" })