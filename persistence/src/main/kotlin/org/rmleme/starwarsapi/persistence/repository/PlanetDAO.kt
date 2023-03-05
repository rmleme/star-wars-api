package org.rmleme.starwarsapi.persistence.repository

import org.rmleme.starwarsapi.persistence.entity.PlanetDocument
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface PlanetDAO : MongoRepository<PlanetDocument, Int> {

    fun findByName(name: String): Optional<PlanetDocument>
}
