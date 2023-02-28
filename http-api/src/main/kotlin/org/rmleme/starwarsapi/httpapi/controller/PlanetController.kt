package org.rmleme.starwarsapi.httpapi.controller

import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import org.rmleme.starwarsapi.httpapi.dto.response.PlanetResponse

fun Route.getPlanets() {
    get {
        call.respond(
            listOf(
                PlanetResponse(name = "Coruscant", climate = "temperate", terrain = "cityscape, mountains"),
                PlanetResponse(name = "Hoth", climate = "frozen", terrain = "tundra, ice caves, mountain ranges"),
            )
        )
    }
}
