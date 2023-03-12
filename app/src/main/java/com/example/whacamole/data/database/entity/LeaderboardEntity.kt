package com.example.whacamole.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leaderboard_table")
data class LeaderboardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val score: Int,
)