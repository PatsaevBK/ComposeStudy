package com.example.composestudy.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.essentyLifecycle
import com.example.composestudy.getApplicationComponent
import com.example.composestudy.navigation.Screens.Home
import com.example.composestudy.navigation.Screens.TrafficLights
import com.example.composestudy.presentation.trafficLight.TrafficLightViewModel

@Composable
internal fun AppNavGraph(
    navHostController: NavHostController,
    homeScreen: @Composable () -> Unit,
    trafficLightScreen: @Composable (viewModel: TrafficLightViewModel) -> Unit,
) {
    NavHost(navController = navHostController, startDestination = Home.toString()) {
        composable(Home.toString()) { homeScreen() }
        composable(TrafficLights.toString()) {
            Log.d("XXX", "composable(TrafficLights)")
            val lifecycle: Lifecycle = remember {
                Log.d("XXX", "it.essentyLifecycle()")
                it.essentyLifecycle()
            }
            val trafficLightComponent =
                getApplicationComponent().getTrafficLightComponentFactory().create(lifecycle)
            val trafficLightViewModel = viewModel<TrafficLightViewModel>(
                viewModelStoreOwner = it,
                factory = trafficLightComponent.getViewModelFactory(),
            )
            trafficLightScreen(trafficLightViewModel)
        }
    }
}