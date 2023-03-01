package org.rmleme.starwarsapi.httpapi.controller

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import io.mockk.Called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.rmleme.starwarsapi.entities.CORUSCANT
import org.rmleme.starwarsapi.httpapi.extension.asJson
import org.rmleme.starwarsapi.httpapi.extension.defaultTestApplication
import org.rmleme.starwarsapi.usecases.service.PlanetService
import org.springframework.core.io.ClassPathResource
import java.util.Optional

class GetPlanetControllerTest : ShouldSpec({

    isolationMode = IsolationMode.InstancePerTest

    val service = mockk<PlanetService>()

    should("return HTTP 400 when id is non-numeric") {
        testApplication {
            val client = defaultTestApplication(PLANETS_PATH) { getPlanet(service) }

            client.get("$PLANETS_PATH/abc").apply {
                status shouldBe HttpStatusCode.BadRequest
            }

            coVerify { service wasNot Called }
        }
    }

    should("return a planet with HTTP 200") {
        testApplication {
            val client = defaultTestApplication(PLANETS_PATH) { getPlanet(service) }

            val planet = CORUSCANT
            val expected = ClassPathResource("/get-planet-response.json").file.readText()

            coEvery { service.findById(9) } returns Optional.of(planet)

            client.get("$PLANETS_PATH/9").apply {
                status shouldBe HttpStatusCode.OK
                bodyAsText().asJson() shouldBe expected.asJson()
            }

            coVerify(exactly = 1) { service.findById(9) }
        }
    }

    should("return no planet with HTTP 404") {
        testApplication {
            val client = defaultTestApplication(PLANETS_PATH) { getPlanet(service) }

            coEvery { service.findById(9) } returns Optional.empty()

            client.get("$PLANETS_PATH/9").apply {
                status shouldBe HttpStatusCode.NotFound
            }

            coVerify(exactly = 1) { service.findById(9) }
        }
    }
})
