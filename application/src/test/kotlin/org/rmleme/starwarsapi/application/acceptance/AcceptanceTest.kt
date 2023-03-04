package org.rmleme.starwarsapi.application.acceptance

import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.server.testing.testApplication
import org.rmleme.starwarsapi.application.acceptance.configuration.AcceptanceTestSetup
import org.rmleme.starwarsapi.entities.CORUSCANT
import org.rmleme.starwarsapi.httpapi.controller.PLANETS_PATH
import org.rmleme.starwarsapi.httpapi.controller.deletePlanet
import org.rmleme.starwarsapi.httpapi.controller.dto.request.PlanetRequest
import org.rmleme.starwarsapi.httpapi.controller.getPlanet
import org.rmleme.starwarsapi.httpapi.controller.getPlanets
import org.rmleme.starwarsapi.httpapi.controller.postPlanet
import org.rmleme.starwarsapi.httpapi.extension.defaultTestApplication
import org.rmleme.starwarsapi.usecases.service.PlanetService
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = [AcceptanceTestSetup::class])
class AcceptanceTest(
    private val service: PlanetService
) : FunSpec({

    test("end-to-end acceptance test") {
        testApplication {
            val client = defaultTestApplication(PLANETS_PATH) {
                postPlanet(service)
                getPlanets(service)
                getPlanet(service)
                deletePlanet(service)
            }

            client.post(PLANETS_PATH) {
                contentType(ContentType.Application.Json)
                setBody(PlanetRequest(id = 9))
            }.apply {
                status shouldBe HttpStatusCode.Created
                service.findAll() shouldBe listOf(CORUSCANT)
            }

            client.get(PLANETS_PATH).apply {
                status shouldBe HttpStatusCode.OK
            }

            client.get("$PLANETS_PATH/?name=Coruscant").apply {
                status shouldBe HttpStatusCode.OK
            }

            client.get("$PLANETS_PATH/9").apply {
                status shouldBe HttpStatusCode.OK
            }

            client.delete("$PLANETS_PATH/9").apply {
                status shouldBe HttpStatusCode.NoContent
                service.findAll().isEmpty() shouldBe true
            }
        }
    }
}) {

    override fun extensions() = listOf(SpringExtension)
}
