package com.todocomposeapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Blue1 = Color(0XFF1565C0)
val Blue2 = Color(0xFF698FBB)

val LightGrey = Color(0XFFFCFCFC)
val MediumGrey = Color(0XFF9C9C9C)
val DarkGrey = Color(0XFF141414)

val LowPriorityColor = Color(0xFF00C980)
val MediumPriorityColor = Color(0XFFFFC114)
val HighPriorityColor = Color(0xFFFF4646)
val NonePriorityColor = MediumGrey


val ColorScheme.splashScreenBackground: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black else Blue1
val ColorScheme.taskItemTextColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray

val ColorScheme.taskItembackgroudColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.DarkGray else Color.White

val ColorScheme.fabContentColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.LightGray else Blue1

val ColorScheme.topAppBarContentColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.LightGray else Color.White

val ColorScheme.topAppBarBackgroumdColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black else Blue1