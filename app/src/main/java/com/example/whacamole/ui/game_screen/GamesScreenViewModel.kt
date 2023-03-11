package com.example.whacamole.ui.game_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class GamesScreenViewModel() : ViewModel() {
    private val delaySpawnNewMol = 3000L
    private val delayVisibleMol = 1000L
    private val _molVisibility = MutableStateFlow(-1)
    val molVisibility = _molVisibility.asStateFlow()
    private val _score = MutableStateFlow(0)
    val score = _score.asStateFlow()

    fun disableMol() {
        viewModelScope.launch {
            _molVisibility.value = -1
        }
    }

    fun activateMol(index: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _molVisibility.value = index
            delay(delayVisibleMol)
            if (_molVisibility.value == index) _molVisibility.value = -1
        }
    }


    fun startGame() {
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                delay(delaySpawnNewMol)
                val index = Random.nextInt(9)
                activateMol(index)
            }
        }
    }

    fun scoreUp() {
        viewModelScope.launch {
            _score.value = _score.value + 1
        }
    }

    init {
        startGame()
    }
}
