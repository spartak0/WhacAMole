package com.example.whacamole.di

import android.content.Context
import androidx.room.Room
import com.example.whacamole.data.database.dao.LeaderboardDao
import com.example.whacamole.data.database.db.LeaderboardDatabase
import com.example.whacamole.domain.mapper.LeaderboardMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideLeaderboardDatabase(@ApplicationContext appContext: Context): LeaderboardDatabase {
        return Room.databaseBuilder(
            appContext,
            LeaderboardDatabase::class.java,
            "leaderboard_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideLeaderboardDao(leaderboardDatabase: LeaderboardDatabase): LeaderboardDao {
        return leaderboardDatabase.leaderboardDao()
    }

    @Provides
    @Singleton
    fun provideLeaderboardMapper(): LeaderboardMapper {
        return LeaderboardMapper()
    }
}