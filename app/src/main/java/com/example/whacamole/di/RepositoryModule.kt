package com.example.whacamole.di

import com.example.whacamole.data.database.dao.LeaderboardDao
import com.example.whacamole.data.repository.DatabaseRepositoryImpl
import com.example.whacamole.domain.mapper.LeaderboardMapper
import com.example.whacamole.domain.repository.DatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesDatabaseRepository(
        dao: LeaderboardDao,
        mapper: LeaderboardMapper
    ): DatabaseRepository {
        return DatabaseRepositoryImpl(dao, mapper)
    }
}