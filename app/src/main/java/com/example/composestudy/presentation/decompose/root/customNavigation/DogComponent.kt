package com.example.composestudy.presentation.decompose.root.customNavigation

import com.arkivanov.decompose.value.Value

interface DogComponent {
    val model: Value<Model>

    data class Model(
        val imageUrl: Images,
        val counter: String
    )
}