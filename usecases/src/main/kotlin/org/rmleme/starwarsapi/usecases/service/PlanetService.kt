package org.rmleme.starwarsapi.usecases.service

import org.rmleme.starwarsapi.entities.planet.Planet
import org.springframework.stereotype.Service

@Service
class PlanetService {

    suspend fun findAll() = listOf(
        Planet(name = "Coruscant", climate = "temperate", terrain = "cityscape, mountains"),
        Planet(name = "Hoth", climate = "frozen", terrain = "tundra, ice caves, mountain ranges"),
    )
}
