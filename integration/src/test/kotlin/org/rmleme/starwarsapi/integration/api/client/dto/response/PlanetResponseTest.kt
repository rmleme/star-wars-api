package org.rmleme.starwarsapi.integration.api.client.dto.response

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class PlanetResponseTest : ShouldSpec({

    should("map all PlanetResponse attributes to Planet attributes") {
        val planetResponse = PlanetResponse(
            name = "Coruscant",
            climate = "temperate",
            terrain = "cityscape, mountains",
            films = emptyList()
        )

        val planet = planetResponse.toPlanet(9, emptyList())

        planet.id shouldBe 9
        planet.name shouldBe planetResponse.name
        planet.climate shouldBe planetResponse.climate
        planet.terrain shouldBe planetResponse.terrain
        planet.films.size shouldBe planetResponse.films.size
    }
})
