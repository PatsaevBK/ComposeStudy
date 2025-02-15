package com.example.composestudy.presentation.trafficLight

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.example.composestudy.presentation.trafficLight.store.TrafficLightController
import com.example.composestudy.presentation.trafficLight.store.TrafficLightView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TrafficLightScreen(
    trafficLightViewModel: TrafficLightViewModel,
    modifier: Modifier,
    title: String,
    onBackPressed: () -> Unit
) {
    val model = trafficLightViewModel.trafficLightViewImpl.model
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = title) }, navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            })
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TrafficLight(
                modifier = modifier.weight(1f),
                selectedColors = model.value.selectedColor.second
            )

            Button(onClick = { trafficLightViewModel.nextColor() }) {
                Text("Click to change")
            }
        }
    }
}

@Preview
@Composable
private fun TrafficLightScreen_Preview() {
    val viewModel = TrafficLightViewModel(object : TrafficLightController {
        override fun onViewCreated(view: TrafficLightView, lifecycle: Lifecycle) {
            TrafficLightView.Model(
                0,
                Pair(
                    TrafficLightView.Model.Colors.entries.toList(),
                    TrafficLightView.Model.Colors.RED
                )
            )
        }

        override fun doOnCleared() {
            TODO("Not yet implemented")
        }
    }, lifecycle = LifecycleRegistry())
    TrafficLightScreen(
        trafficLightViewModel = viewModel,
        modifier = Modifier,
        title = "Something"
    ) {

    }
}