package com.example.whacamole.utils
interface Mapper<Domain, Entity> {
    fun entityToDomain(entity: Entity):Domain
    fun domainToEntity(domain: Domain):Entity
}