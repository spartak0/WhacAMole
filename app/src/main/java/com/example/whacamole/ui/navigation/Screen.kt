package com.example.whacamole.ui.navigation

sealed class Screen(val route: String) {
    object StartScreen : Screen("start_screen")
    object GameScreen : Screen("game_screen")
    object ScoreScreen : Screen("score_screen")
    object LeaderboardScreen : Screen("leaderboard_screen")
}
