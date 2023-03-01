package org.rmleme.starwarsapi.httpapi.controller

import io.ktor.http.HttpStatusCode
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

fun Route.getPlanet(service: PlanetService) {
    get("/{id}") {
        val planet = call.parameters["id"]?.let { service.findById(it.toInt()) }
        if (planet?.isPresent == true) {
            call.respond(planet.get())
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }
}
