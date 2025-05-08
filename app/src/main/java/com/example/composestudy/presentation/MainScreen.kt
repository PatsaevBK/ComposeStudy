package com.example.composestudy.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composestudy.navigation.AppNavGraph
import com.example.composestudy.navigation.Screens
import com.example.composestudy.navigation.rememberNavigationState
import com.example.composestudy.presentation.decompose.CustomNavigationContent
import com.example.composestudy.presentation.decompose.root.RootComponent
import com.example.composestudy.presentation.homeScreen.HomeScreen
import com.example.composestudy.presentation.manyStores.ManyStoresScreen
import com.example.composestudy.presentation.trafficLight.TrafficLightScreen

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    rootComponent: RootComponent,
) {
    val navigationState = rememberNavigationState()

    Scaffold(modifier) {
        AppNavGraph(
            navHostController = navigationState.navHostController,
            homeScreen = {
                HomeScreen(modifier = modifier.padding(it)) {
                    navigationState.navigateTo(it)
                }
            },
            trafficLightScreen = { trafficLightViewModel ->
                TrafficLightScreen(
                    trafficLightViewModel = trafficLightViewModel,
                    modifier.padding(it),
                    title = Screens.TrafficLights.toString()
                ) { navigationState.navHostController.popBackStack() }
            },
            manyStoresScreen = { manyStoresViewModel ->
                ManyStoresScreen(
                    manyStoresViewModel = manyStoresViewModel,
                    title = Screens.ManyStores.toString(),
                    modifier = modifier.padding(it),
                    onBackPressed = { navigationState.navHostController.popBackStack() }
                )
            },
            decompose = {
                CustomNavigationContent(rootComponent)
            }
        )
    }
}
