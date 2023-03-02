package org.rmleme.starwarsapi.integration.api.client

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.rmleme.starwarsapi.entities.CORUSCANT
import org.rmleme.starwarsapi.integration.api.client.dto.response.PlanetResponse
import org.rmleme.starwarsapi.integration.api.configuration.ApiClientConfiguration
import org.rmleme.starwarsapi.integration.http.HttpClient
import org.springframework.core.io.ClassPathResource
import java.io.IOException
import java.util.Optional

class SWApiClientImplTest : ShouldSpec({

    isolationMode = IsolationMode.InstancePerTest

    val httpClient = mockk<HttpClient>()
    val apiClient = SWApiClientImpl(httpClient, SWAPI_URL, ApiClientConfiguration().jackson())

    should("return a planet when invoke an existent planet id from swapi") {
        val expected = CORUSCANT

        coEvery { httpClient.get("$SWAPI_URL/planets/9/?format=json") } returns ClassPathResource(
            "/swapi-planet-9-response.json"
        ).file.readText()
        coEvery { httpClient.get(filmUrl(3)) } returns ClassPathResource("/swapi-film-3-response.json").file.readText()
        coEvery { httpClient.get(filmUrl(4)) } returns ClassPathResource("/swapi-film-4-response.json").file.readText()
        coEvery { httpClient.get(filmUrl(5)) } returns ClassPathResource("/swapi-film-5-response.json").file.readText()
        coEvery { httpClient.get(filmUrl(6)) } returns ClassPathResource("/swapi-film-6-response.json").file.readText()

        val result = apiClient.loadPlanetFromApi(9)

        result.get() shouldBe expected

        coVerify(exactly = 5) { httpClient.get(any()) }
    }

    should("return Optional.empty when invoke a non-existent planet id from swapi") {
        val expected = Optional.empty<PlanetResponse>()

        coEvery { httpClient.get("$SWAPI_URL/planets/9/?format=json") } throws IOException()

        val result = apiClient.loadPlanetFromApi(9)

        result shouldBe expected

        coVerify(exactly = 1) { httpClient.get(any()) }
    }
})

private const val SWAPI_URL = "https://swapi.dev/api"

private fun filmUrl(id: Int) = "$SWAPI_URL/films/$id/?format=json"
