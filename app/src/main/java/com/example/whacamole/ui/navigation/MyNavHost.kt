package com.example.whacamole.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.whacamole.ui.game_screen.GameScreen
import com.example.whacamole.ui.start_screen.StartScreen
import com.example.whacamole.utils.Constants

@Composable
fun MyNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.StartScreen.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.StartScreen.route) {
            StartScreen(navigateToGame = {
                navController.navigate(Screen.GameScreen.route)
            })
        }
        composable(
            Screen.GameScreen.route
        ) {
            GameScreen()
        }
    }
}