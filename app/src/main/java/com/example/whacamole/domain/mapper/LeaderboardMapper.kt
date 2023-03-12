package com.example.whacamole.domain.mapper

import com.example.whacamole.data.database.entity.LeaderboardEntity
import com.example.whacamole.domain.model.LeaderboardData
import com.example.whacamole.utils.Mapper

class LeaderboardMapper : Mapper<LeaderboardData, LeaderboardEntity> {
    override fun entityToDomain(entity: LeaderboardEntity): LeaderboardData {
        return LeaderboardData(entity.id, entity.name, entity.score)
    }

    override fun domainToEntity(domain: LeaderboardData): LeaderboardEntity {
        return LeaderboardEntity(domain.id, domain.name, domain.score)
    }
}