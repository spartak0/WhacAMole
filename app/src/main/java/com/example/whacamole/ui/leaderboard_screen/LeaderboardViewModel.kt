package com.example.whacamole.ui.leaderboard_screen

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
class LeaderboardViewModel @Inject constructor(private val databaseRepository: DatabaseRepository) :
    ViewModel() {
    private val _leaderboard = MutableStateFlow(listOf<LeaderboardData>())
    val leaderboard = _leaderboard.asStateFlow()

    init {
        fetchLeaderboard()
    }

    fun fetchLeaderboard() = viewModelScope.launch(Dispatchers.IO) {
        databaseRepository.fetchAllLeaderboard().collect {
            _leaderboard.value = it
        }
    }

    fun clearLeaderboard() = viewModelScope.launch(Dispatchers.IO) {
        databaseRepository.clearLeaderboard()
    }
}