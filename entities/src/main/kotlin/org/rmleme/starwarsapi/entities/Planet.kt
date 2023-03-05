package org.rmleme.starwarsapi.entities

data class Planet(
    val id: Int,
    val name: String,
    val climate: String,
    val terrain: String,
    val films: List<Film>
)
