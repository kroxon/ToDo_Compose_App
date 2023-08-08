package com.todocomposeapp.navigation.destinations

import android.util.Log
import android.window.SplashScreen
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.navArgument
import com.todocomposeapp.ui.screens.list.ListScreen
import com.todocomposeapp.ui.screens.splash.SplashScreen
import com.todocomposeapp.ui.viewmodels.SharedViewModel
import com.todocomposeapp.util.Constants.LIST_ARGUMENT_KEY
import com.todocomposeapp.util.Constants.LIST_SCREEN
import com.todocomposeapp.util.Constants.SPLASH_SCREEN
import com.todocomposeapp.util.toAction

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit
) {
    composable(
        route = SPLASH_SCREEN,
        exitTransition = {
            slideOutVertically(
                targetOffsetY = { fullHeight ->  fullHeight},
                animationSpec = tween(durationMillis = 2000)
            )
        }
    ) {
        SplashScreen(navigateToListScreen = navigateToListScreen)
    }
}

