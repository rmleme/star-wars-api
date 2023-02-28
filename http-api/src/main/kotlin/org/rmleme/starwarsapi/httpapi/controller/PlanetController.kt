package org.rmleme.starwarsapi.httpapi.controller

import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import org.rmleme.starwarsapi.usecases.service.PlanetService

fun Route.getPlanets(service: PlanetService) {
    get {
        call.respond(service.findAll())
    }
}
