package com.example.whacamole.domain.repository

import com.example.whacamole.domain.model.LeaderboardData
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    suspend fun fetchTopFiveLeaderboard(): Flow<List<LeaderboardData>>
    suspend fun clearLeaderboard()
    suspend fun insertLeader(leaderboardData: LeaderboardData)
}