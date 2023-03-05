package org.rmleme.starwarsapi.persistence.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.rmleme.starwarsapi.entities.Planet
import org.rmleme.starwarsapi.persistence.entity.toDocument
import org.rmleme.starwarsapi.usecases.adapter.PlanetRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
class PlanetRepositoryImpl(private val dao: PlanetDAO) : PlanetRepository {

    override suspend fun save(id: Int, planet: Planet) = dao.save(planet.toDocument(id)).toEntity()

    override suspend fun findAll() = withContext(Dispatchers.IO) {
        dao.findAll().map { it.toEntity() }
    }

    override suspend fun findById(id: Int): Optional<Planet> = withContext(Dispatchers.IO) {
        dao.findById(id).map { it.toEntity() }
    }

    override suspend fun findByName(name: String): Optional<Planet> = withContext(Dispatchers.IO) {
        dao.findByName(name).map { it.toEntity() }
    }

    override suspend fun deleteById(id: Int): Optional<Planet> {
        val planetDocument = dao.findById(id)
        return if (planetDocument.isPresent) {
            dao.delete(planetDocument.get())
            Optional.of(planetDocument.get().toEntity())
        } else {
            Optional.empty()
        }
    }
}
