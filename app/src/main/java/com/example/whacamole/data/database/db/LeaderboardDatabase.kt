package com.example.whacamole.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.whacamole.data.database.dao.LeaderboardDao
import com.example.whacamole.data.database.entity.LeaderboardEntity

@Database(entities = [LeaderboardEntity::class], version = 2)
abstract class LeaderboardDatabase : RoomDatabase() {
    abstract fun leaderboardDao(): LeaderboardDao
}