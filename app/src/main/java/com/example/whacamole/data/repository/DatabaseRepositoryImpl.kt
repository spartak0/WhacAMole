package com.example.whacamole.data.repository

import com.example.whacamole.data.database.dao.LeaderboardDao
import com.example.whacamole.domain.mapper.LeaderboardMapper
import com.example.whacamole.domain.model.LeaderboardData
import com.example.whacamole.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatabaseRepositoryImpl(
    private val dao: LeaderboardDao,
    private val leaderboardMapper: LeaderboardMapper
) : DatabaseRepository {

    override suspend fun fetchTopFiveLeaderboard(): Flow<List<LeaderboardData>> {
        return dao.getTopFive().map { list ->
            list.map { leaderboardMapper.entityToDomain(it) }
        }
    }

    override suspend fun clearLeaderboard() {
        dao.clearLeaderboard()
    }

    override suspend fun insertLeader(leaderboardData: LeaderboardData) {
        dao.addLeader(leaderboardMapper.domainToEntity(leaderboardData))
    }
}