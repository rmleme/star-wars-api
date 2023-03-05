package org.rmleme.starwarsapi.persistence.entity

import org.rmleme.starwarsapi.entities.Film
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "films")
data class FilmDocument(
    val title: String,
    val director: String,
    val releaseDate: String
) {

    fun toEntity() = Film(
        title = title,
        director = director,
        releaseDate = releaseDate
    )
}

fun Film.toDocument() = FilmDocument(
    title = title,
    director = director,
    releaseDate = releaseDate
)
