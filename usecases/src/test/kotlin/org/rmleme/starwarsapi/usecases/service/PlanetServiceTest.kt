package org.rmleme.starwarsapi.usecases.service

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.rmleme.starwarsapi.entities.planet.Film
import org.rmleme.starwarsapi.entities.planet.Planet
import org.rmleme.starwarsapi.usecases.adapter.PlanetRepository

class PlanetServiceTest : ShouldSpec({

    val planetRepository = mockk<PlanetRepository>()
    val service = PlanetService(planetRepository)

    should("delegate find all planets to repository") {
        val planets = listOf(
            Planet(
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
            Planet(
                name = "Hoth",
                climate = "frozen",
                terrain = "tundra, ice caves, mountain ranges",
                films = listOf(
                    Film(title = "The Empire Strikes Back", director = "Irvin Kershner", releaseDate = "1980-05-17")
                )
            )
        )

        coEvery { planetRepository.findAll() } returns planets

        val result = service.findAll()

        result shouldBe planets

        coVerify(exactly = 1) { planetRepository.findAll() }
    }
})
