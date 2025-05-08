package com.example.composestudy.presentation.decompose.root

import com.arkivanov.decompose.Child
import com.arkivanov.decompose.value.Value

interface RootComponent {
    val children: Value<Children<*, DogComponent>>

    class Children<out C : Any, out T : Any>(
        val items: List<Child.Created<C, T>>,
        val index: Int,
        val mode: Mode,
    )

    enum class Mode {
        CAROUSEL, PAGER
    }
}