package com.example.whacamole.ui.score_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.whacamole.domain.model.LeaderboardData

@Composable
fun ScoreScreen(viewModel: ScoreViewModel = hiltViewModel(), score: Int) {
    val leaderboard = viewModel.leaderboard.collectAsState()
    viewModel.fetchTopFiveLeaderboard()
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = score.toString())
        Button(onClick = { viewModel.insertLeader(LeaderboardData(0, "spartak", score)) }) {
            Text(text = "insert")
        }
        LazyColumn {
            items(leaderboard.value.size) {
                Text(text = "${leaderboard.value[it].name} - ${leaderboard.value[it].score}")
            }
        }
    }
}