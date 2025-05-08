package com.example.composestudy.presentation.decompose.root

import com.arkivanov.decompose.value.Value

interface DogComponent {
    val model: Value<Model>

    data class Model(
        val imageUrl: String,
        val counter: String
    )
}