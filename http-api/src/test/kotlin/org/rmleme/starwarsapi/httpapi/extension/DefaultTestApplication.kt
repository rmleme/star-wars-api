package org.rmleme.starwarsapi.httpapi.extension

import io.ktor.client.HttpClient
import io.ktor.serialization.jackson.jackson
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.IgnoreTrailingSlash
import io.ktor.server.routing.Route
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import io.ktor.server.testing.ApplicationTestBuilder

fun ApplicationTestBuilder.defaultTestApplication(path: String = "", myRouting: Route.() -> Unit): HttpClient {
    application {
        install(ContentNegotiation) { jackson {} }
        install(IgnoreTrailingSlash)
        routing {
            route(path) {
                myRouting()
            }
        }
    }
    return createClient { install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) { jackson {} } }
}
