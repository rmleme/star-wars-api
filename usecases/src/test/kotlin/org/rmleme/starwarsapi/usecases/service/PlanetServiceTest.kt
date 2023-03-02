package org.rmleme.starwarsapi.usecases.service

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.Called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.rmleme.starwarsapi.entities.CORUSCANT
import org.rmleme.starwarsapi.entities.buildPlanets
import org.rmleme.starwarsapi.usecases.adapter.PlanetRepository
import org.rmleme.starwarsapi.usecases.adapter.SWApiClient
import java.util.Optional

class PlanetServiceTest : ShouldSpec({

    isolationMode = IsolationMode.InstancePerTest

    val swapiClient = mockk<SWApiClient>()
    val planetRepository = mockk<PlanetRepository>()
    val service = PlanetService(swapiClient, planetRepository)

    should("delegate load a planet to swapi client") {
        val planet = CORUSCANT

        coEvery { swapiClient.loadPlanetFromApi(9) } returns Optional.of(planet)

        val result = service.loadPlanetFromApi(9)

        result.get() shouldBe planet

        coVerify(exactly = 1) { swapiClient.loadPlanetFromApi(9) }
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

    should("delegate delete a planet to repository") {
        val planet = CORUSCANT

        coEvery { planetRepository.deleteById(9) } returns Optional.of(planet)

        val result = service.deleteById(9)

        result.get() shouldBe planet

        coVerify { swapiClient wasNot Called }
        coVerify(exactly = 1) { planetRepository.deleteById(9) }
    }
})
