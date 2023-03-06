package org.rmleme.starwarsapi.usecases.service

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.Called
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import org.rmleme.starwarsapi.entities.CORUSCANT
import org.rmleme.starwarsapi.entities.Planet
import org.rmleme.starwarsapi.entities.buildPlanets
import org.rmleme.starwarsapi.usecases.adapter.PlanetRepository
import org.rmleme.starwarsapi.usecases.adapter.SWApiClient
import java.util.Optional

class PlanetServiceTest : ShouldSpec({

    isolationMode = IsolationMode.InstancePerTest

    val swapiClient = mockk<SWApiClient>()
    val planetRepository = mockk<PlanetRepository>()
    val service = PlanetService(swapiClient, planetRepository)

    should("load a planet from swapi client and persist in repository") {
        val planet = CORUSCANT

        coEvery { swapiClient.loadPlanetFromApi(9) } returns Optional.of(planet)
        coEvery { planetRepository.save(9, planet) } returns planet

        val result = service.loadPlanetFromApi(9)

        result.get() shouldBe planet

        coVerify(exactly = 1) {
            swapiClient.loadPlanetFromApi(9)
            planetRepository.save(9, planet)
        }
    }

    should("does not load a planet from swapi client and does not persist in repository") {
        val expected = Optional.empty<Planet>()

        coEvery { swapiClient.loadPlanetFromApi(999) } returns Optional.empty<Planet>()

        val result = service.loadPlanetFromApi(999)

        result shouldBe expected

        coVerify(exactly = 1) { swapiClient.loadPlanetFromApi(999) }
        coVerify { planetRepository wasNot Called }
    }

    should("delegate find all planets to repository") {
        val planets = buildPlanets()

        coEvery { planetRepository.findAll() } returns planets

        val result = service.findAll()

        result shouldBe planets

        coVerify { swapiClient wasNot Called }
        coVerify(exactly = 1) { planetRepository.findAll() }
    }

    should("delegate find a planet by id to repository") {
        val planet = CORUSCANT

        coEvery { planetRepository.findById(9) } returns Optional.of(planet)

        val result = service.findById(9)

        result.get() shouldBe planet

        coVerify { swapiClient wasNot Called }
        coVerify(exactly = 1) { planetRepository.findById(9) }
    }

    should("delegate find a planet by name to repository") {
        val planet = CORUSCANT

        coEvery { planetRepository.findByName("Coruscant") } returns Optional.of(planet)

        val result = service.findByName("Coruscant")

        result.get() shouldBe planet

        coVerify { swapiClient wasNot Called }
        coVerify(exactly = 1) { planetRepository.findByName("Coruscant") }
    }

    should("delete an existent planet from repository") {
        val planet = CORUSCANT

        coEvery { planetRepository.findById(9) } returns Optional.of(planet)
        coEvery { planetRepository.deleteById(9) } just Runs

        val result = service.deleteById(9)

        result.get() shouldBe planet

        coVerify { swapiClient wasNot Called }
        coVerify(exactly = 1) {
            planetRepository.findById(9)
            planetRepository.deleteById(9)
        }
    }

    should("return an empty Optional when delete a non-existent planet") {
        coEvery { planetRepository.findById(9) } returns Optional.empty()

        val result = service.deleteById(9)

        result.isEmpty shouldBe true

        coVerify { swapiClient wasNot Called }
        coVerify(exactly = 1) {
            planetRepository.findById(9)
        }
        coVerify(exactly = 0) {
            planetRepository.deleteById(9)
        }
    }
})
