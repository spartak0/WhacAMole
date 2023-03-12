package com.example.whacamole.ui.game_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.whacamole.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    viewModel: GamesScreenViewModel = hiltViewModel(),
    navigateUp: () -> Unit,
    navigateToScore: (Int) -> Unit,
) {
    val molVisibility = viewModel.molVisibility.collectAsState()
    val score = viewModel.score.collectAsState()
    val time = viewModel.time.collectAsState()

    if (time.value == 30) {
        LaunchedEffect(true ){
            Log.d("AAA", "GameScreen: time.value == 30")
            viewModel.clear()
            navigateToScore(score.value)
        }

    }

    Scaffold(topBar = {
        TopBar(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            score = score.value,
            time = time.value,
            navigateUp = navigateUp
        )
    }) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(10.dp),
                userScrollEnabled = false
            ) {
                items(9) { index ->
                    val boxSize = ((LocalConfiguration.current.screenWidthDp / 3) - 10).dp
                    val visible = index == molVisibility.value
                    val hitEffect = remember { mutableStateOf(false) }
                    val borderDp =
                        animateDpAsState(targetValue = if (hitEffect.value) 4.dp else 0.dp)
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .size(boxSize)
                            .clip(CircleShape)
                            .background(Color.Black)
                            .border(borderDp.value, Color(0xFF8BC34A), CircleShape)
                            .clickable {
                                if (visible) {
                                    viewModel.scoreUp()
                                    viewModel.disableMol()
                                    hitEffect.value = true
                                }
                            }
                    ) {
                        AnimatedVisibility(
                            visible = visible,
                            enter = slideInVertically(
                                animationSpec = tween(50, 0),
                                initialOffsetY = { it }),
                            exit = slideOutVertically(
                                animationSpec = tween(50, 0),
                                targetOffsetY = { it })
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.mole),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp)
                                    .absoluteOffset(y = 10.dp),
                                contentDescription = null
                            )
                        }
                        LaunchedEffect(hitEffect.value) {
                            delay(500)
                            hitEffect.value = false
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun TopBar(modifier: Modifier, score: Int, time: Int, navigateUp: () -> Unit) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
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
            text = "Score: $score",
            style = MaterialTheme.typography.headlineLarge
        )
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(50.dp)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = time.toString(),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

