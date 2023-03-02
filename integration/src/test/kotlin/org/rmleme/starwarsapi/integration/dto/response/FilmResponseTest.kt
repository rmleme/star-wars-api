package org.rmleme.starwarsapi.integration.dto.response

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import org.rmleme.starwarsapi.integration.api.client.dto.response.FilmResponse

class FilmResponseTest : ShouldSpec({

    should("map all FilmResponse attributes to Film attributes") {
        val filmResponse = FilmResponse(
            title = "Return of the Jedi",
            director = "Richard Marquand",
            releaseDate = "1983-05-25"
        )

        val film = filmResponse.toFilm()

        film.title shouldBe filmResponse.title
        film.director shouldBe filmResponse.director
        film.releaseDate shouldBe filmResponse.releaseDate
    }
})
