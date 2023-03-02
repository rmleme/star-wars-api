package org.rmleme.starwarsapi.integration.api.client

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.rmleme.starwarsapi.entities.Planet
import org.rmleme.starwarsapi.integration.api.client.dto.response.FilmResponse
import org.rmleme.starwarsapi.integration.api.client.dto.response.PlanetResponse
import org.rmleme.starwarsapi.integration.http.HttpClient
import org.rmleme.starwarsapi.usecases.adapter.SWApiClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Optional

@Component
class SWApiClientImpl(
    private val httpClient: HttpClient,
    @Value("\${swapi.url}") private val swapiUrl: String,
    private val mapper: ObjectMapper
) : SWApiClient {

    override suspend fun loadPlanetFromApi(id: Int): Optional<Planet> = coroutineScope {
        val planetJson = httpClient.get("$swapiUrl/planets/$id/?format=json")
        val planetResponse = mapper.readValue(planetJson, PlanetResponse::class.java)
        val filmsResponse = planetResponse.films.map { filmUrl ->
            async {
                val filmJson = httpClient.get("$filmUrl?format=json")
                mapper.readValue(filmJson, FilmResponse::class.java)
            }
        }.awaitAll()
        Optional.of(planetResponse.toPlanet(filmsResponse))
    }
}
