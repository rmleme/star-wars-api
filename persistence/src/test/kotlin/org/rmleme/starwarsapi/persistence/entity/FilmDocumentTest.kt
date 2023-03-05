package org.rmleme.starwarsapi.persistence.entity

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import org.rmleme.starwarsapi.entities.Film

class FilmDocumentTest : ShouldSpec({

    should("map FilmDocument attributes to Film attributes") {
        val filmDocument = FilmDocument(
            title = "Return of the Jedi",
            director = "Richard Marquand",
            releaseDate = "1983-05-25"
        )

        val film = filmDocument.toEntity()

        film.title shouldBe filmDocument.title
        film.director shouldBe filmDocument.director
        film.releaseDate shouldBe filmDocument.releaseDate
    }

    should("map all Film attributes to FilmDocument attributes") {
        val film = Film(
            title = "Return of the Jedi",
            director = "Richard Marquand",
            releaseDate = "1983-05-25"
        )

        val filmDocument = film.toDocument()

        filmDocument.title shouldBe film.title
        filmDocument.director shouldBe film.director
        filmDocument.releaseDate shouldBe film.releaseDate
    }
})
