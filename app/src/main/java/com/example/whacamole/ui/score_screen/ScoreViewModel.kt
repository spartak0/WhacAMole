package com.example.whacamole.ui.score_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whacamole.domain.model.LeaderboardData
import com.example.whacamole.domain.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
) : ViewModel() {
    private val _leaderboard = MutableStateFlow(listOf<LeaderboardData>())
    val leaderboard = _leaderboard.asStateFlow()

    private val _enabled = MutableStateFlow(true)
    val enabled = _enabled.asStateFlow()

    init {
        fetchTopFiveLeaderboard()
    }

    fun changeEnabled(value: Boolean) = viewModelScope.launch {
        _enabled.value = value
    }

    fun fetchTopFiveLeaderboard() = viewModelScope.launch(Dispatchers.IO) {
        databaseRepository.fetchTopTenLeaderboard().collect {
            _leaderboard.value = it
        }
    }

    fun insertLeader(leaderboardData: LeaderboardData) =
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.insertLeader(leaderboardData)
        }


}