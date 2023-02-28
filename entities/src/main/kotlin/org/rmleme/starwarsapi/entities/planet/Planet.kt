package org.rmleme.starwarsapi.entities.planet

data class Planet(
    val name: String,
    val climate: String,
    val terrain: String,
    val films: List<Film>
)
