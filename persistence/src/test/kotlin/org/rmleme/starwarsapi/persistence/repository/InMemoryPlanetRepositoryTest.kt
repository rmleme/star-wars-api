package org.rmleme.starwarsapi.persistence.repository

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import org.rmleme.starwarsapi.entities.CORUSCANT
import org.rmleme.starwarsapi.entities.HOTH
import org.rmleme.starwarsapi.entities.buildPlanets

class InMemoryPlanetRepositoryTest : ShouldSpec({

    isolationMode = IsolationMode.InstancePerTest

    val repository = InMemoryPlanetRepository()

    beforeEach {
        repository.save(9, CORUSCANT)
        repository.save(4, HOTH)
    }

    afterTest {
        repository.deleteById(9)
        repository.deleteById(4)
    }

    should("save a planet") {
        val planet = CORUSCANT

        val result = repository.save(9, planet)

        result shouldBe planet
        repository.findById(9).get() shouldBe planet
    }

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

    should("delete an existent planet") {
        val planet = CORUSCANT

        val result = repository.deleteById(9)

        result.isPresent shouldBe true
        result.get() shouldBe planet
    }

    should("return an empty Optional when delete a non-existent planet") {
        val result = repository.deleteById(-1)

        result.isEmpty shouldBe true
    }
})
