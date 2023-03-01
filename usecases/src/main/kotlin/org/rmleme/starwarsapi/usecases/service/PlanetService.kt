package org.rmleme.starwarsapi.usecases.service

import org.rmleme.starwarsapi.usecases.adapter.PlanetRepository
import org.springframework.stereotype.Service

@Service
class PlanetService(val repository: PlanetRepository) {

    suspend fun findAll() = repository.findAll()

    suspend fun findById(id: Int) = repository.findById(id)

    suspend fun findByName(name: String) = repository.findByName(name)
}
