package org.rmleme.starwarsapi.httpapi

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import org.rmleme.starwarsapi.httpapi.controller.getPlanets
import org.rmleme.starwarsapi.httpapi.extension.asJson
import org.rmleme.starwarsapi.httpapi.extension.defaultTestApplication
import org.springframework.core.io.ClassPathResource

class GetPlanetsControllerTest : ShouldSpec({

    isolationMode = IsolationMode.InstancePerTest

    should("return list of planets with HTTP 200") {
        testApplication {
            val client = defaultTestApplication(PLANETS_PATH) { getPlanets() }

            val expected = ClassPathResource("/get-planets-response.json").file.readText()

            client.get(PLANETS_PATH).apply {
                status shouldBe HttpStatusCode.OK
                bodyAsText().asJson() shouldBe expected.asJson()
            }
        }
    }
})

const val PLANETS_PATH = "/v1.0/planets"
