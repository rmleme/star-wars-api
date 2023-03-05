package org.rmleme.starwarsapi.application.acceptance

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.extensions.wiremock.ListenerMode
import io.kotest.extensions.wiremock.WireMockListener
import io.kotest.matchers.shouldBe
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.server.testing.testApplication
import org.rmleme.starwarsapi.application.acceptance.AcceptanceTest.Companion.WIREMOCK_SERVER_URL
import org.rmleme.starwarsapi.application.acceptance.configuration.AcceptanceTestSetup
import org.rmleme.starwarsapi.entities.CORUSCANT
import org.rmleme.starwarsapi.httpapi.controller.PLANETS_PATH
import org.rmleme.starwarsapi.httpapi.controller.deletePlanet
import org.rmleme.starwarsapi.httpapi.controller.dto.request.PlanetRequest
import org.rmleme.starwarsapi.httpapi.controller.getPlanet
import org.rmleme.starwarsapi.httpapi.controller.getPlanets
import org.rmleme.starwarsapi.httpapi.controller.postPlanet
import org.rmleme.starwarsapi.httpapi.extension.asJson
import org.rmleme.starwarsapi.httpapi.extension.defaultTestApplication
import org.rmleme.starwarsapi.usecases.service.PlanetService
import org.springframework.core.io.ClassPathResource
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource

@ContextConfiguration(classes = [AcceptanceTestSetup::class])
@TestPropertySource(properties = ["swapi.url=$WIREMOCK_SERVER_URL"])
class AcceptanceTest(
    private val service: PlanetService
) : FunSpec({

    listener(WireMockListener(wireMockServer, ListenerMode.PER_SPEC))

    test("end-to-end acceptance test") {
        testApplication {
            val client = defaultTestApplication(PLANETS_PATH) {
                postPlanet(service)
                getPlanets(service)
                getPlanet(service)
                deletePlanet(service)
            }

            val expectedSingle = ClassPathResource("/http-api/single-planet-response.json").file.readText()
            val expectedMultiple = ClassPathResource("/http-api/multiple-planets-response.json").file.readText()

            buildSWApiStubs(wireMockServer)

            service.findAll().isEmpty() shouldBe true

            client.post(PLANETS_PATH) {
                contentType(ContentType.Application.Json)
                setBody(PlanetRequest(id = 9))
            }.apply {
                status shouldBe HttpStatusCode.Created
                bodyAsText().asJson() shouldBe expectedSingle.asJson()
            }
            service.findAll() shouldBe listOf(CORUSCANT)

            client.get(PLANETS_PATH).apply {
                status shouldBe HttpStatusCode.OK
                bodyAsText().asJson() shouldBe expectedMultiple.asJson()
            }

            client.get("$PLANETS_PATH/?name=Coruscant").apply {
                status shouldBe HttpStatusCode.OK
                bodyAsText().asJson() shouldBe expectedSingle.asJson()
            }

            client.get("$PLANETS_PATH/9").apply {
                status shouldBe HttpStatusCode.OK
                bodyAsText().asJson() shouldBe expectedSingle.asJson()
            }

            client.delete("$PLANETS_PATH/9").apply {
                status shouldBe HttpStatusCode.NoContent
            }

            service.findAll().isEmpty() shouldBe true
        }
    }
}) {

    override fun extensions() = listOf(SpringExtension)

    companion object {
        private const val HOST = "http://localhost"
        private const val PORT = 9090
        const val WIREMOCK_SERVER_URL = "$HOST:$PORT"
        private val wireMockServer = WireMockServer(PORT)
    }
}

fun buildSWApiStubs(wireMockServer: WireMockServer) {
    wireMockServer.stubFor(
        WireMock.get(WireMock.urlEqualTo("/planets/9/?format=json")).willReturn(
            WireMock.ok().withBody(ClassPathResource("/integration/swapi-planet-9-response.json").file.readText())
        )
    )
    wireMockServer.stubFor(
        WireMock.get(WireMock.urlEqualTo("/films/3/?format=json")).willReturn(
            WireMock.ok().withBody(ClassPathResource("/integration/swapi-film-3-response.json").file.readText())
        )
    )
    wireMockServer.stubFor(
        WireMock.get(WireMock.urlEqualTo("/films/4/?format=json")).willReturn(
            WireMock.ok().withBody(ClassPathResource("/integration/swapi-film-4-response.json").file.readText())
        )
    )
    wireMockServer.stubFor(
        WireMock.get(WireMock.urlEqualTo("/films/5/?format=json")).willReturn(
            WireMock.ok().withBody(ClassPathResource("/integration/swapi-film-5-response.json").file.readText())
        )
    )
    wireMockServer.stubFor(
        WireMock.get(WireMock.urlEqualTo("/films/6/?format=json")).willReturn(
            WireMock.ok().withBody(ClassPathResource("/integration/swapi-film-6-response.json").file.readText())
        )
    )
}
