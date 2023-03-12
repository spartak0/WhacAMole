package com.example.whacamole.ui.leaderboard_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.whacamole.R

@Composable
fun LeaderboardScreen(viewModel: LeaderboardViewModel = hiltViewModel(), navigateUp: () -> Unit) {
    val leaderboard = viewModel.leaderboard.collectAsState()
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        LeaderboardTopBar(
            modifier = Modifier
                .padding(start = 24.dp, top = 16.dp)
                .fillMaxWidth()
        ) { navigateUp() }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            items(leaderboard.value.size) {
                Text(
                    text = "${leaderboard.value[it].name} - ${leaderboard.value[it].score}",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
        Button(
            onClick = { viewModel.clearLeaderboard() },
            modifier = Modifier
                .padding(bottom = 32.dp, top = 16.dp)
        ) {
            Text(text = stringResource(R.string.clear_leaderboard))
        }

    }
}

@Composable
fun LeaderboardTopBar(modifier: Modifier, navigateUp: () -> Unit) {
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clip(CircleShape)
                .size(50.dp)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            IconButton(onClick = { navigateUp() }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = null
                )
            }
        }
        Text(
            text = stringResource(R.string.leaderboard),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}