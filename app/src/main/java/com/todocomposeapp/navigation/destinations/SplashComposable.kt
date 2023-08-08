package com.todocomposeapp.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.todocomposeapp.ui.screens.splash.SplashScreen
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

