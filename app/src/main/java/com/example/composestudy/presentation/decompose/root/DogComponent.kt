package com.example.composestudy.presentation.decompose.root

import com.arkivanov.decompose.value.Value
import com.example.composestudy.presentation.decompose.Images

interface DogComponent {
    val model: Value<Model>

    data class Model(
        val imageUrl: Images,
        val counter: String
    )
}