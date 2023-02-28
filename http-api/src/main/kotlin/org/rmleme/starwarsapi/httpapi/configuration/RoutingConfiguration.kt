package org.rmleme.starwarsapi.httpapi.configuration

import io.ktor.server.application.Application
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import org.rmleme.starwarsapi.httpapi.controller.getPlanets
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RoutingConfiguration {

    @Bean
    fun configureRoutes(
        application: Application
    ) = with(application) {
        routing {
            route(VERSION_ROUTE) {
                route(PLANETS_ROUTE) {
                    getPlanets()
                }
            }
        }
    }
}

private const val VERSION_ROUTE = "/v1.0"
private const val PLANETS_ROUTE = "/planets"
