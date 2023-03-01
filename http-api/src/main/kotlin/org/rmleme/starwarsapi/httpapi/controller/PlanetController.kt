package org.rmleme.starwarsapi.httpapi.controller

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import org.rmleme.starwarsapi.usecases.service.PlanetService

fun Route.getPlanets(service: PlanetService) {
    get {
        val name = call.request.queryParameters["name"]
        if (name == null) {
            call.respond(service.findAll())
        } else {
            getPlanetByName(call, service, name)
        }
    }
}

suspend fun getPlanetByName(call: ApplicationCall, service: PlanetService, name: String) {
    val planet = service.findByName(name)
    if (planet.isPresent) {
        call.respond(planet.get())
    } else {
        call.respond(HttpStatusCode.NotFound)
    }
}

fun Route.getPlanet(service: PlanetService) {
    get("/{id}") {
        val id = call.parameters["id"]?.toIntOrNull()
        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, "Invalid id: ${call.parameters["id"]}. Must be numeric.")
        } else {
            val planet = service.findById(id)
            if (planet.isPresent) {
                call.respond(planet.get())
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}
