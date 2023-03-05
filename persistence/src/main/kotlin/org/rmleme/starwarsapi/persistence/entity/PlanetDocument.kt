package org.rmleme.starwarsapi.persistence.entity

import org.rmleme.starwarsapi.entities.Planet
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "planets")
data class PlanetDocument(
    @Id val id: Int,
    @Indexed val name: String,
    val climate: String,
    val terrain: String,
    val films: List<FilmDocument>
) {

    fun toEntity() = Planet(
        id = id,
        name = name,
        climate = climate,
        terrain = terrain,
        films = films.map { it.toEntity() }
    )
}

fun Planet.toDocument(id: Int) = PlanetDocument(
    id = id,
    name = name,
    climate = climate,
    terrain = terrain,
    films = films.map { it.toDocument() }
)
