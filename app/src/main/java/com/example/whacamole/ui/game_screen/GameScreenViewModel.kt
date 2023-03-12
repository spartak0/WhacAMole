package com.example.whacamole.ui.game_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class GameScreenViewModel @Inject constructor() : ViewModel() {
    private val startGameJob: Job by lazy { startGame() }
    private val startTimeJob: Job by lazy { startTimer() }

    private val delaySpawnNewMol = 2500L
    private val delayVisibleMol = 550L

    private val _molVisibility = MutableStateFlow(-1)
    val molVisibility = _molVisibility.asStateFlow()

    private val _score = MutableStateFlow(0)
    val score = _score.asStateFlow()

    private val _time = MutableStateFlow(0)
    val time = _time.asStateFlow()

    init {
        startGameJob.start()
        startTimeJob.start()
    }

    fun disableMol() = viewModelScope.launch {
        _molVisibility.value = -1
    }

    fun activateMol(index: Int) = viewModelScope.launch(Dispatchers.IO) {
        _molVisibility.value = index
        delay(delayVisibleMol)
        if (_molVisibility.value == index) _molVisibility.value = -1
    }

    fun startGame() = viewModelScope.launch(Dispatchers.IO) {
        while (true) {
            delay(delaySpawnNewMol)
            val index = Random.nextInt(9)
            activateMol(index)
        }
    }

    fun scoreUp() = viewModelScope.launch {
        _score.value = _score.value + 1
    }

    private fun startTimer() = viewModelScope.launch(Dispatchers.IO) {
        while (_time.value < 30) {
            Log.d("AAA", "startTimer: timer active")
            delay(1000)
            _time.value = _time.value + 1
        }

    }

    fun clear() {
        startGameJob.cancel()
        startTimeJob.cancel()
    }

}
