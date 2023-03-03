package org.rmleme.starwarsapi.integration.api.client

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.rmleme.starwarsapi.entities.Planet
import org.rmleme.starwarsapi.integration.api.client.dto.response.FilmResponse
import org.rmleme.starwarsapi.integration.api.client.dto.response.PlanetResponse
import org.rmleme.starwarsapi.integration.exception.HttpNotFoundException
import org.rmleme.starwarsapi.integration.http.HttpClient
import org.rmleme.starwarsapi.usecases.adapter.SWApiClient
import org.slf4j.LoggerFactory
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
        val planetJson = try {
            httpClient.get("$swapiUrl/planets/$id/?format=json")
        } catch (e: HttpNotFoundException) {
            logger.warn("Planet $id not found", e)
            return@coroutineScope Optional.empty()
        }

        val planetResponse = mapper.readValue(planetJson, PlanetResponse::class.java)
        val filmsResponse = planetResponse.films.map { filmUrl ->
            async {
                val filmJson = httpClient.get("$filmUrl?format=json")
                mapper.readValue(filmJson, FilmResponse::class.java)
            }
        }.awaitAll()

        logger.info("Planet $id (${planetResponse.name}) retrieved from swapi")
        Optional.of(planetResponse.toPlanet(filmsResponse))
    }

    private companion object {
        val logger = LoggerFactory.getLogger(SWApiClientImpl::class.java)!!
    }
}
