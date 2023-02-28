package org.rmleme.starwarsapi.persistence.repository

import org.rmleme.starwarsapi.entities.planet.Film
import org.rmleme.starwarsapi.entities.planet.Planet
import org.rmleme.starwarsapi.usecases.adapter.PlanetRepository
import org.springframework.stereotype.Repository

@Suppress("MagicNumber")
@Repository
class InMemoryPlanetRepository : PlanetRepository {

    private val registry = mutableMapOf(
        9 to Planet(
            name = "Coruscant",
            climate = "temperate",
            terrain = "cityscape, mountains",
            films = listOf(
                Film(title = "Return of the Jedi", director = "Richard Marquand", releaseDate = "1983-05-25"),
                Film(title = "The Phantom Menace", director = "George Lucas", releaseDate = "1999-05-19"),
                Film(title = "Attack of the Clones", director = "George Lucas", releaseDate = "2002-05-16"),
                Film(title = "Revenge of the Sith", director = "George Lucas", releaseDate = "2005-05-19")
            )
        ),
        4 to Planet(
            name = "Hoth",
            climate = "frozen",
            terrain = "tundra, ice caves, mountain ranges",
            films = listOf(
                Film(title = "The Empire Strikes Back", director = "Irvin Kershner", releaseDate = "1980-05-17")
            )
        )
    )

    override suspend fun findAll() = registry.values.toList()
}
