package org.rmleme.starwarsapi.persistence.entity

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import org.rmleme.starwarsapi.entities.Planet

class PlanetDocumentTest : ShouldSpec({

    should("map PlanetDocument attributes to Planet attributes") {
        val planetDocument = PlanetDocument(
            id = 9,
            name = "Coruscant",
            climate = "temperate",
            terrain = "cityscape, mountains",
            films = emptyList()
        )

        val planet = planetDocument.toEntity()

        planet.name shouldBe planetDocument.name
        planet.climate shouldBe planetDocument.climate
        planet.terrain shouldBe planetDocument.terrain
        planet.films.size shouldBe planetDocument.films.size
    }

    should("map all Planet attributes to PlanetDocument attributes") {
        val planet = Planet(
            id = 9,
            name = "Coruscant",
            climate = "temperate",
            terrain = "cityscape, mountains",
            films = emptyList()
        )

        val planetDocument = planet.toDocument(9)

        planetDocument.id shouldBe 9
        planetDocument.name shouldBe planet.name
        planetDocument.climate shouldBe planet.climate
        planetDocument.terrain shouldBe planet.terrain
        planetDocument.films.size shouldBe planet.films.size
    }
})
