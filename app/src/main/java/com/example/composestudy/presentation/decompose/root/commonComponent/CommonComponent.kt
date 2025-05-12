package com.example.composestudy.presentation.decompose.root.commonComponent

import kotlinx.coroutines.flow.StateFlow

interface CommonComponent {
    val model: StateFlow<Model>

    data class Model(val buttons: List<String>)

    fun navigateTo(child: String)
}