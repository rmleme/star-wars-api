package org.rmleme.starwarsapi.persistence.repository

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.rmleme.starwarsapi.entities.CORUSCANT
import org.rmleme.starwarsapi.entities.HOTH
import org.rmleme.starwarsapi.persistence.entity.toDocument
import java.util.Optional

class PlanetRepositoryImplTest : ShouldSpec({

    isolationMode = IsolationMode.InstancePerTest

    val planetDAO = mockk<PlanetDAO>()
    val repository = PlanetRepositoryImpl(planetDAO)

    should("save a planet") {
        val planet = CORUSCANT
        val planetDocument = planet.toDocument(9)

        every { planetDAO.save(planetDocument) } returns planetDocument

        val result = repository.save(9, planet)

        result shouldBe planet

        verify(exactly = 1) { planetDAO.save(planetDocument) }
    }

    should("find all planets") {
        val planets = listOf(CORUSCANT, HOTH)
        val planetDocuments = listOf(CORUSCANT.toDocument(9), HOTH.toDocument(4))

        every { planetDAO.findAll() } returns planetDocuments

        val result = repository.findAll()

        result.size shouldBe planets.size
        result.all { it in planets } shouldBe true

        verify(exactly = 1) { planetDAO.findAll() }
    }

    should("find a planet by id") {
        val planet = CORUSCANT
        val planetDocument = planet.toDocument(9)

        every { planetDAO.findById(9) } returns Optional.of(planetDocument)

        val result = repository.findById(9)

        result.isPresent shouldBe true
        result.get() shouldBe planet

        verify(exactly = 1) { planetDAO.findById(9) }
    }

    should("find no planets by id") {
        every { planetDAO.findById(-1) } returns Optional.empty()

        val result = repository.findById(-1)

        result.isEmpty shouldBe true

        verify(exactly = 1) { planetDAO.findById(-1) }
    }

    should("find a planet by name") {
        val planet = CORUSCANT
        val planetDocument = planet.toDocument(9)

        every { planetDAO.findByName("Coruscant") } returns Optional.of(planetDocument)

        val result = repository.findByName("Coruscant")

        result.isPresent shouldBe true
        result.get() shouldBe planet

        verify(exactly = 1) { planetDAO.findByName("Coruscant") }
    }

    should("find no planets by name") {
        every { planetDAO.findByName("") } returns Optional.empty()

        val result = repository.findByName("")

        result.isEmpty shouldBe true

        verify(exactly = 1) { planetDAO.findByName("") }
    }

    should("delete a planet") {
        every { planetDAO.deleteById(9) } just Runs

        repository.deleteById(9)

        verify(exactly = 1) {
            planetDAO.deleteById(9)
        }
    }
})
