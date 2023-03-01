package org.rmleme.starwarsapi.persistence.repository

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import org.rmleme.starwarsapi.entities.CORUSCANT
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

    should("find a planet by id") {
        val planet = CORUSCANT

        val result = repository.findById(9)

        result.isPresent shouldBe true
        result.get() shouldBe planet
    }

    should("find no planets by id") {
        val result = repository.findById(-1)

        result.isEmpty shouldBe true
    }

    should("find a planet by name") {
        val planet = CORUSCANT

        val result = repository.findByName("Coruscant")

        result.isPresent shouldBe true
        result.get() shouldBe planet
    }

    should("find no planets by name") {
        val result = repository.findByName("")

        result.isEmpty shouldBe true
    }
})
