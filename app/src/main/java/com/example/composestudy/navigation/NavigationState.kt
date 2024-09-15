package com.example.composestudy.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

internal class NavigationState(
    val navHostController: NavHostController
) {

    fun navigateTo(route: Screens) {
        navHostController.navigate(route) {
//            пока не нужно (позволяет убрать все из стека навигации до стартового экрана = Home)
//            popUpTo(navHostController.graph.findStartDestination().id) {
//                saveState = true
//            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Composable
internal fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    return remember {
        NavigationState(navHostController)
    }
}