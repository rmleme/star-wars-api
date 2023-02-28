package org.rmleme.starwarsapi.httpapi.controller

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.rmleme.starwarsapi.entities.planet.Film
import org.rmleme.starwarsapi.entities.planet.Planet
import org.rmleme.starwarsapi.httpapi.extension.asJson
import org.rmleme.starwarsapi.httpapi.extension.defaultTestApplication
import org.rmleme.starwarsapi.usecases.service.PlanetService
import org.springframework.core.io.ClassPathResource

class GetPlanetsControllerTest : ShouldSpec({

    isolationMode = IsolationMode.InstancePerTest

    val service = mockk<PlanetService>()

    should("return list of planets with HTTP 200") {
        testApplication {
            val client = defaultTestApplication(PLANETS_PATH) { getPlanets(service) }

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
            val expected = ClassPathResource("/get-planets-response.json").file.readText()

            coEvery { service.findAll() } returns planets

            client.get(PLANETS_PATH).apply {
                status shouldBe HttpStatusCode.OK
                bodyAsText().asJson() shouldBe expected.asJson()
            }

            coVerify(exactly = 1) { service.findAll() }
        }
    }
})

const val PLANETS_PATH = "/v1.0/planets"
