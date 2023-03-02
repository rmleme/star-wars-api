package org.rmleme.starwarsapi.usecases.service

import org.rmleme.starwarsapi.usecases.adapter.PlanetRepository
import org.rmleme.starwarsapi.usecases.adapter.SWApiClient
import org.springframework.stereotype.Service

@Service
class PlanetService(
    private val apiClient: SWApiClient,
    private val repository: PlanetRepository
) {

    suspend fun loadPlanetFromApi(id: Int) = apiClient.loadPlanetFromApi(id)

    suspend fun findAll() = repository.findAll()

    suspend fun findById(id: Int) = repository.findById(id)

    suspend fun findByName(name: String) = repository.findByName(name)

    suspend fun deleteById(id: Int) = repository.deleteById(id)
}
