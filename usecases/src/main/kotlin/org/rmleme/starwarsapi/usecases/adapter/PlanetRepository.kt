package org.rmleme.starwarsapi.usecases.adapter

import org.rmleme.starwarsapi.entities.planet.Planet

interface PlanetRepository {

    suspend fun findAll(): List<Planet>
}
