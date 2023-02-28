package org.rmleme.starwarsapi.usecases.service

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.rmleme.starwarsapi.entities.buildPlanets
import org.rmleme.starwarsapi.usecases.adapter.PlanetRepository

class PlanetServiceTest : ShouldSpec({

    val planetRepository = mockk<PlanetRepository>()
    val service = PlanetService(planetRepository)

    should("delegate find all planets to repository") {
        val planets = buildPlanets()

        coEvery { planetRepository.findAll() } returns planets

        val result = service.findAll()

        result shouldBe planets

        coVerify(exactly = 1) { planetRepository.findAll() }
    }
})
