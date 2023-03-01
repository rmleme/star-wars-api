package org.rmleme.starwarsapi.integration.api.client

import org.rmleme.starwarsapi.entities.Planet
import org.rmleme.starwarsapi.usecases.adapter.PlanetApiClient
import org.springframework.stereotype.Component
import java.util.Optional

@Component
class PlanetApiClientImpl : PlanetApiClient {

    override suspend fun loadPlanetFromApi(id: Int): Optional<Planet> {
        TODO()
    }
}
