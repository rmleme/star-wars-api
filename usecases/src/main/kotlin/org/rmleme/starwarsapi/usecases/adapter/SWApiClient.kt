package org.rmleme.starwarsapi.usecases.adapter

import org.rmleme.starwarsapi.entities.Planet
import java.util.Optional

interface SWApiClient {

    suspend fun loadPlanetFromApi(id: Int): Optional<Planet>
}
