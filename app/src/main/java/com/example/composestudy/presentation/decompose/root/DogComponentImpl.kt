package com.example.composestudy.presentation.decompose.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.example.composestudy.presentation.decompose.Images

class DogComponentImpl(
    componentContext: ComponentContext,
    private val imageUrl: Images,
) : DogComponent, ComponentContext by componentContext {
    override val model: Value<DogComponent.Model>
        get() = TODO("Not yet implemented")
}