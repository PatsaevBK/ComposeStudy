package com.example.composestudy.presentation.decompose.root.commonComponent

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun CommonContent(commonComponent: CommonComponent, modifier: Modifier = Modifier) {
    val model by commonComponent.model.collectAsState()

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(model.buttons) {
            Button(onClick = { commonComponent.navigateTo(it) }) {
                Text(text = it)
            }
        }
    }
}