package org.rmleme.starwarsapi.usecases.service

import org.rmleme.starwarsapi.entities.Planet
import org.rmleme.starwarsapi.usecases.adapter.PlanetRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class PlanetService(val repository: PlanetRepository) {

    @Suppress("UnusedPrivateMember")
    suspend fun loadPlanetFromSWApi(id: Int): Optional<Planet> {
        TODO()
    }

    suspend fun findAll() = repository.findAll()

    suspend fun findById(id: Int) = repository.findById(id)

    suspend fun findByName(name: String) = repository.findByName(name)

    suspend fun deleteById(id: Int) = repository.deleteById(id)
}
