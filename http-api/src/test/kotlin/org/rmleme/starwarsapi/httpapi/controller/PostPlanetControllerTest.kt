package org.rmleme.starwarsapi.httpapi.controller

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.server.testing.testApplication
import io.mockk.called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import org.rmleme.starwarsapi.entities.CORUSCANT
import org.rmleme.starwarsapi.httpapi.controller.dto.request.PlanetRequest
import org.rmleme.starwarsapi.httpapi.extension.asJson
import org.rmleme.starwarsapi.httpapi.extension.defaultTestApplication
import org.rmleme.starwarsapi.usecases.service.PlanetService
import org.springframework.core.io.ClassPathResource
import java.util.Optional

class PostPlanetControllerTest : ShouldSpec({

    isolationMode = IsolationMode.InstancePerTest

    val service = mockk<PlanetService>()

    should("return HTTP 400 when id is null") {
        testApplication {
            val client = defaultTestApplication(PLANETS_PATH) { postPlanet(service) }

            client.post(PLANETS_PATH) {
                contentType(ContentType.Application.Json)
                setBody(
                    """
                    {
                      "id": null
                    }
                    """.trimIndent()
                )
            }.apply {
                status shouldBe HttpStatusCode.BadRequest
            }

            verify { service wasNot called }
        }
    }

    should("save a planet and return HTTP 201") {
        testApplication {
            val client = defaultTestApplication(PLANETS_PATH) { postPlanet(service) }

            val planet = CORUSCANT
            val expected = ClassPathResource("/single-planet-response.json").file.readText()

            coEvery { service.loadPlanetFromApi(9) } returns Optional.of(planet)

            client.post(PLANETS_PATH) {
                contentType(ContentType.Application.Json)
                setBody(PlanetRequest(id = 9))
            }.apply {
                status shouldBe HttpStatusCode.Created
                bodyAsText().asJson() shouldBe expected.asJson()
            }

            coVerify(exactly = 1) { service.loadPlanetFromApi(9) }
        }
    }

    should("return HTTP 422 when planet does not exist in swapi") {
        testApplication {
            val client = defaultTestApplication(PLANETS_PATH) { postPlanet(service) }

            coEvery { service.loadPlanetFromApi(9) } returns Optional.empty()

            client.post(PLANETS_PATH) {
                contentType(ContentType.Application.Json)
                setBody(PlanetRequest(id = 9))
            }.apply {
                status shouldBe HttpStatusCode.UnprocessableEntity
            }

            coVerify(exactly = 1) { service.loadPlanetFromApi(9) }
        }
    }
})
