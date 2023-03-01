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
import org.rmleme.starwarsapi.entities.CORUSCANT
import org.rmleme.starwarsapi.httpapi.extension.asJson
import org.rmleme.starwarsapi.httpapi.extension.defaultTestApplication
import org.rmleme.starwarsapi.usecases.service.PlanetService
import org.springframework.core.io.ClassPathResource
import java.util.Optional

class GetPlanetByNameControllerTest : ShouldSpec({

    isolationMode = IsolationMode.InstancePerTest

    val service = mockk<PlanetService>()

    should("return a planet with HTTP 200") {
        testApplication {
            val client = defaultTestApplication(PLANETS_PATH) { getPlanets(service) }

            val planet = CORUSCANT
            val expected = ClassPathResource("/single-planet-response.json").file.readText()

            coEvery { service.findByName("Coruscant") } returns Optional.of(planet)

            client.get("$PLANETS_PATH/?name=Coruscant").apply {
                status shouldBe HttpStatusCode.OK
                bodyAsText().asJson() shouldBe expected.asJson()
            }

            coVerify(exactly = 1) { service.findByName("Coruscant") }
        }
    }

    should("return no planet with HTTP 404") {
        testApplication {
            val client = defaultTestApplication(PLANETS_PATH) { getPlanets(service) }

            coEvery { service.findByName("Coruscant") } returns Optional.empty()

            client.get("$PLANETS_PATH/?name=Coruscant").apply {
                status shouldBe HttpStatusCode.NotFound
            }

            coVerify(exactly = 1) { service.findByName("Coruscant") }
        }
    }
})
