package com.example.composestudy.presentation.manyStores

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.example.composestudy.presentation.manyStores.store.SharedManyStoresController
import com.example.composestudy.presentation.manyStores.store.SharedManyStoresView
import com.example.composestudy.presentation.trafficLight.store.TrafficLightView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManyStoresScreen(
    manyStoresViewModel: ManyStoresViewModel,
    title: String,
    onBackPressed: () -> Unit,
    modifier: Modifier,
) {
    val model by manyStoresViewModel.mviView.model
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            LazyColumn {
                items(model.trafficLights) { HorizontalTrafficLights(model = it) }
            }
        }
    }
}

@Composable
fun HorizontalTrafficLights(model: TrafficLightView.Model) {
//    TrafficLight(modifier = Modifier, selectedColors = model.selectedColor.second)
    Card {
        Canvas(modifier = Modifier.size(60.dp), contentDescription = "") {
            drawCircle(color = Color.Yellow, radius = 20f)
        }
    }
}

@Composable
@Preview
fun ManyStoresScreen_Preview() {
    ManyStoresScreen(
        manyStoresViewModel = ManyStoresViewModel(
            LifecycleRegistry(),
            object : SharedManyStoresController {
//                override var routeEventCallback: (SharedManyStoresView.RouteEvent) -> Unit = {}

                override fun onViewCreated(view: SharedManyStoresView, viewLifecycle: Lifecycle) {
//                    SharedManyStoresView.Model(listOf(Mo))
                }
            }
        ),
        title = "ManyStoresScreen",
        onBackPressed = { /*TODO*/ },
        modifier = Modifier
    )
}