package com.example.whacamole.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.whacamole.ui.game_screen.GameScreen
import com.example.whacamole.ui.score_screen.ScoreScreen
import com.example.whacamole.ui.score_screen.ScoreViewModel
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
            GameScreen(
                navigateUp = { navController.navigateUp() },
                navigateToScore = { navController.navigate("${Screen.ScoreScreen.route}/$it") })
        }
        composable("${Screen.ScoreScreen.route}/{${Constants.SCORE_ARGUMENT}}",
            arguments = listOf(
                navArgument(Constants.SCORE_ARGUMENT) {
                    type = NavType.IntType
                }
            )) {
            val viewModel:ScoreViewModel = hiltViewModel()
            ScoreScreen(viewModel, score = it.arguments?.getInt(Constants.SCORE_ARGUMENT) ?: 0)
        }
    }
}