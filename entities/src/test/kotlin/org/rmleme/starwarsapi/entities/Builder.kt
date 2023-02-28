package org.rmleme.starwarsapi.entities

fun buildPlanets() = listOf(CORUSCANT, HOTH)

val CORUSCANT = Planet(
    name = "Coruscant",
    climate = "temperate",
    terrain = "cityscape, mountains",
    films = listOf(
        Film(title = "Return of the Jedi", director = "Richard Marquand", releaseDate = "1983-05-25"),
        Film(title = "The Phantom Menace", director = "George Lucas", releaseDate = "1999-05-19"),
        Film(title = "Attack of the Clones", director = "George Lucas", releaseDate = "2002-05-16"),
        Film(title = "Revenge of the Sith", director = "George Lucas", releaseDate = "2005-05-19")
    )
)

val HOTH = Planet(
    name = "Hoth",
    climate = "frozen",
    terrain = "tundra, ice caves, mountain ranges",
    films = listOf(
        Film(title = "The Empire Strikes Back", director = "Irvin Kershner", releaseDate = "1980-05-17")
    )
)
