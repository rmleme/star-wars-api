package org.rmleme.starwarsapi.persistence.repository

import org.rmleme.starwarsapi.entities.planet.Planet
import org.rmleme.starwarsapi.usecases.adapter.PlanetRepository
import org.springframework.stereotype.Repository

@Suppress("MagicNumber")
@Repository
class InMemoryPlanetRepository : PlanetRepository {

    private val registry = mutableMapOf(
        9 to Planet(name = "Coruscant", climate = "temperate", terrain = "cityscape, mountains"),
        4 to Planet(name = "Hoth", climate = "frozen", terrain = "tundra, ice caves, mountain ranges")
    )

    override suspend fun findAll() = registry.values.toList()
}
