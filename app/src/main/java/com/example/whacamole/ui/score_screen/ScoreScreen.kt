package com.example.whacamole.ui.score_screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.whacamole.R
import com.example.whacamole.domain.model.LeaderboardData
import com.example.whacamole.ui.view.CircleBtn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoreScreen(
    viewModel: ScoreViewModel = hiltViewModel(),
    score: Int,
    navigateToGame: () -> Unit,
    navigateToMenu: () -> Unit,
    navigateToLeaderboard: () -> Unit,
) {
    val leaderboard = viewModel.leaderboard.collectAsState()
    var tfValue by remember { mutableStateOf("") }
    val maxSizeName = 16
    val enabled = viewModel.enabled.collectAsState()
    val sendBtnColor = animateColorAsState(
        targetValue = if (enabled.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
            alpha = 0.3f
        )
    )
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Your current score - $score",
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextField(
                    value = tfValue,
                    onValueChange = { if (it.length < maxSizeName) tfValue = it },
                    modifier = Modifier.width(200.dp),
                    enabled = enabled.value,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.width(4.dp))
                CircleBtn(modifier = Modifier.size(50.dp), background = sendBtnColor.value) {
                    if (tfValue.isNotEmpty()) {
                        viewModel.insertLeader(
                            LeaderboardData(
                                0,
                                tfValue,
                                score
                            )
                        )
                        tfValue = ""
                        viewModel.changeEnabled(false)
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)) {
                    Text(
                        text = stringResource(R.string.leaderboard),
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                CircleBtn(modifier = Modifier.size(30.dp)) {
                    navigateToLeaderboard()
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                items(leaderboard.value.size) {
                    Text(
                        text = "${leaderboard.value[it].name} - ${leaderboard.value[it].score}",
                        style = MaterialTheme.typography.headlineMedium.copy(fontSize = 24.sp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
            ) {
                Button(onClick = { navigateToGame() }) {
                    Text(text = stringResource(R.string.play_again), style = MaterialTheme.typography.bodyLarge)
                }
                Button(onClick = { navigateToMenu() }) {
                    Text(text = stringResource(R.string.menu), style = MaterialTheme.typography.bodyLarge)
                }

            }
        }

    }

}