package org.rmleme.starwarsapi.usecases.adapter

import org.rmleme.starwarsapi.entities.Planet
import java.util.Optional

interface PlanetRepository {

    suspend fun save(id: Int, planet: Planet): Planet

    suspend fun findAll(): List<Planet>

    suspend fun findById(id: Int): Optional<Planet>

    suspend fun findByName(name: String): Optional<Planet>

    suspend fun deleteById(id: Int)
}
