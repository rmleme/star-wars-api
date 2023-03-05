package org.rmleme.starwarsapi.integration.api.client.dto.response

import org.rmleme.starwarsapi.entities.Planet

data class PlanetResponse(
    val name: String,
    val climate: String,
    val terrain: String,
    val films: List<String>
) {
    fun toPlanet(id: Int, films: List<FilmResponse>) = Planet(
        id = id,
        name = name,
        climate = climate,
        terrain = terrain,
        films = films.map { it.toFilm() }
    )
}
