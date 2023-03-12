package com.example.whacamole.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.whacamole.data.database.entity.LeaderboardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LeaderboardDao {

    @Query("SELECT * FROM leaderboard_table")
    fun getTopFive(): Flow<List<LeaderboardEntity>>

    @Insert
    suspend fun addLeader(leader: LeaderboardEntity)

    @Query("DELETE FROM leaderboard_table")
    suspend fun clearLeaderboard()
}