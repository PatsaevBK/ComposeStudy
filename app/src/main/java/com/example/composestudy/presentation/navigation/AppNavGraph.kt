package com.example.composestudy.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    trafficLightScreen: @Composable () -> Unit,
) {
    NavHost(navController = navHostController, startDestination = Main.toString()) {
        composable(TrafficLights.toString()) { trafficLightScreen() }
    }
}