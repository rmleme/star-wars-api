package org.rmleme.starwarsapi.persistence.repository

import org.rmleme.starwarsapi.entities.Planet
import org.rmleme.starwarsapi.usecases.adapter.PlanetRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Suppress("MagicNumber")
@Repository
class InMemoryPlanetRepository : PlanetRepository {

    private val registry = mutableMapOf<Int, Planet>()

    override suspend fun save(id: Int, planet: Planet): Planet {
        registry[id] = planet
        return planet
    }

    override suspend fun findAll() = registry.values.toList()

    override suspend fun findById(id: Int) = Optional.ofNullable(registry[id])

    override suspend fun findByName(name: String) = Optional.ofNullable(registry.values.firstOrNull { it.name == name })

    override suspend fun deleteById(id: Int): Optional<Planet> = Optional.ofNullable(registry.remove(id))
}
