package org.rmleme.starwarsapi.persistence.repository

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import org.rmleme.starwarsapi.entities.buildPlanets

class InMemoryPlanetRepositoryTest : ShouldSpec({

    isolationMode = IsolationMode.InstancePerTest

    val repository = InMemoryPlanetRepository()

    should("find all planets") {
        val planets = buildPlanets()

        val result = repository.findAll()

        result.size shouldBe planets.size
        result.all { it in planets } shouldBe true
    }
})
