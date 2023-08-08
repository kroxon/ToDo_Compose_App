package com.todocomposeapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.todocomposeapp.navigation.destinations.listComposable
//import com.todocomposeapp.navigation.destinations.splashComposable
import com.todocomposeapp.navigation.destinations.taskComposable
import com.todocomposeapp.ui.viewmodels.SharedViewModel
import com.todocomposeapp.util.Constants.LIST_SCREEN
import com.todocomposeapp.util.Constants.SPLASH_SCREEN

@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }
    NavHost(
        navController = navController,
        startDestination = LIST_SCREEN
    ) {
//        splashComposable(
//            navigateToListScreen = screen.splashScreen
//        )
        listComposable(
            navigateToTaskScreen = screen.list,
            sharedViewModel = sharedViewModel
        )
        taskComposable(
            sharedViewModel = sharedViewModel,
            navigateToListScreen = screen.task
        )
    }
}


