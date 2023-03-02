package org.rmleme.starwarsapi.integration.api.client.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import org.rmleme.starwarsapi.entities.Film

data class FilmResponse(
    val title: String,
    val director: String,
    @JsonProperty("release_date") val releaseDate: String
) {
    fun toFilm() = Film(
        title = title,
        director = director,
        releaseDate = releaseDate
    )
}
