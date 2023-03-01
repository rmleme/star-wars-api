package org.rmleme.starwarsapi.httpapi.controller

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import io.mockk.Called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.rmleme.starwarsapi.entities.CORUSCANT
import org.rmleme.starwarsapi.httpapi.extension.defaultTestApplication
import org.rmleme.starwarsapi.usecases.service.PlanetService
import java.util.Optional

class DeletePlanetControllerTest : ShouldSpec({

    isolationMode = IsolationMode.InstancePerTest

    val service = mockk<PlanetService>()

    should("return HTTP 400 when id is non-numeric") {
        testApplication {
            val client = defaultTestApplication(PLANETS_PATH) { deletePlanet(service) }

            client.delete("$PLANETS_PATH/abc").apply {
                status shouldBe HttpStatusCode.BadRequest
            }

            coVerify { service wasNot Called }
        }
    }

    should("return HTTP 204") {
        testApplication {
            val client = defaultTestApplication(PLANETS_PATH) { deletePlanet(service) }

            val planet = CORUSCANT

            coEvery { service.deleteById(9) } returns Optional.of(planet)

            client.delete("$PLANETS_PATH/9").apply {
                status shouldBe HttpStatusCode.NoContent
            }

            coVerify(exactly = 1) { service.deleteById(9) }
        }
    }

    should("return HTTP 404") {
        testApplication {
            val client = defaultTestApplication(PLANETS_PATH) { deletePlanet(service) }

            coEvery { service.deleteById(9) } returns Optional.empty()

            client.delete("$PLANETS_PATH/9").apply {
                status shouldBe HttpStatusCode.NotFound
            }

            coVerify(exactly = 1) { service.deleteById(9) }
        }
    }
})
