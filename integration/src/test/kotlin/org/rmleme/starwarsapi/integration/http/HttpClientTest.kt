package org.rmleme.starwarsapi.integration.http

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.extensions.wiremock.ListenerMode
import io.kotest.extensions.wiremock.WireMockListener
import io.kotest.matchers.shouldBe
import org.rmleme.starwarsapi.integration.exception.HttpNotFoundException
import java.io.IOException

class HttpClientTest : ShouldSpec({

    val client = HttpClient()

    listener(WireMockListener(wireMockServer, ListenerMode.PER_SPEC))

    should("return content body when HTTP GET is successful") {
        val expected = RESPONSE_JSON

        wireMockServer.stubFor(
            WireMock.get(WireMock.urlEqualTo(TEST_URL)).willReturn(
                WireMock.ok().withBody(RESPONSE_JSON)
            )
        )

        val result = client.get("$WIREMOCK_SERVER_URL$TEST_URL")

        result shouldBe expected
    }

    should("throw HttpNotFoundException when HTTP GET returns 404") {
        wireMockServer.stubFor(
            WireMock.get(WireMock.urlEqualTo(TEST_URL)).willReturn(
                WireMock.notFound()
            )
        )

        shouldThrow<HttpNotFoundException> { client.get("$WIREMOCK_SERVER_URL$TEST_URL") }
    }

    should("throw IOException when HTTP GET returns other errors != 404") {
        wireMockServer.stubFor(
            WireMock.get(WireMock.urlEqualTo(TEST_URL)).willReturn(
                WireMock.serverError()
            )
        )

        shouldThrow<IOException> { client.get("$WIREMOCK_SERVER_URL$TEST_URL") }
    }
}) {

    companion object {
        private const val HOST = "http://localhost"
        private const val PORT = 9090
        const val WIREMOCK_SERVER_URL = "$HOST:$PORT"
        private val wireMockServer = WireMockServer(PORT)
        private const val RESPONSE_JSON = "{ 'name': 'Coruscant' }"
        private const val TEST_URL = "/planets/9/?format=json"
    }
}
