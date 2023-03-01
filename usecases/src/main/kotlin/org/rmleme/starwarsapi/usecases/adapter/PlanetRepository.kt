package org.rmleme.starwarsapi.usecases.adapter

import org.rmleme.starwarsapi.entities.Planet
import java.util.Optional

interface PlanetRepository {

    suspend fun findAll(): List<Planet>

    suspend fun findById(id: Int): Optional<Planet>
}
