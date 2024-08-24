package com.example.composestudy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composestudy.navigation.Screens.Home
import com.example.composestudy.navigation.Screens.TrafficLights

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    homeScreen: @Composable () -> Unit,
    trafficLightScreen: @Composable () -> Unit,
) {
    NavHost(navController = navHostController, startDestination = Home.toString()) {
        composable(Home.toString()) { homeScreen() }
        composable(TrafficLights.toString()) { trafficLightScreen() }
    }
}