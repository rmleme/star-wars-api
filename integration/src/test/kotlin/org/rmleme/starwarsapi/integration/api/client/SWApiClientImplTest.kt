package org.rmleme.starwarsapi.integration.api.client

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.rmleme.starwarsapi.entities.CORUSCANT
import org.rmleme.starwarsapi.integration.api.configuration.ApiClientConfiguration
import org.rmleme.starwarsapi.integration.http.HttpClient
import org.springframework.core.io.ClassPathResource

class SWApiClientImplTest : ShouldSpec({

    val httpClient = mockk<HttpClient>()
    val swapiUrl = "https://swapi.dev/api"
    val apiClient = SWApiClientImpl(httpClient, swapiUrl, ApiClientConfiguration().jackson())

    should("get a planet from swapi") {
        val expected = CORUSCANT

        coEvery { httpClient.get("$swapiUrl/planets/9/?format=json") } returns ClassPathResource(
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
})

private fun filmUrl(id: Int) = "https://swapi.dev/api/films/$id/?format=json"
