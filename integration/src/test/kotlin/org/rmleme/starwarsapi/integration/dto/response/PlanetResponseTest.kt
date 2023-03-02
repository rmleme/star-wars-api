package org.rmleme.starwarsapi.integration.dto.response

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import org.rmleme.starwarsapi.integration.api.client.dto.response.PlanetResponse

class PlanetResponseTest : ShouldSpec({

    should("map all PlanetResponse attributes to Planet attributes") {
        val planetResponse = PlanetResponse(
            name = "Coruscant",
            climate = "temperate",
            terrain = "cityscape, mountains",
            films = emptyList()
        )

        val planet = planetResponse.toPlanet(emptyList())

        planet.name shouldBe planetResponse.name
        planet.climate shouldBe planetResponse.climate
        planet.terrain shouldBe planetResponse.terrain
        planet.films.size shouldBe planetResponse.films.size
    }
})
