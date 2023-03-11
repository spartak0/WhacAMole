package com.example.whacamole.ui.game_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.whacamole.R

@Composable
fun GameScreen(viewModel: GamesScreenViewModel = viewModel()) {
    val molVisibility = viewModel.molVisibility.collectAsState()
    val score = viewModel.score.collectAsState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        LazyVerticalGrid(columns = GridCells.Fixed(3), contentPadding = PaddingValues(10.dp)) {
            items(9) { index ->
                val boxSize = ((LocalConfiguration.current.screenWidthDp / 3) - 10).dp
                val visible = index == molVisibility.value
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .size(boxSize)
                        .clip(CircleShape)
                        .background(Color.Black)
                        .clickable {
                            if (visible) {
                                viewModel.scoreUp()
                                viewModel.disableMol()
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

                }
            }
        }

        Text(text = "Score: ${score.value}", modifier = Modifier.padding(top=20.dp).align(Alignment.TopCenter))
    }
}

