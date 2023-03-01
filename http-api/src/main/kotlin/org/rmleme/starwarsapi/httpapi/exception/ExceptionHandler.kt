package org.rmleme.starwarsapi.httpapi.exception

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.plugins.requestvalidation.RequestValidationException
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import org.slf4j.LoggerFactory.getLogger

object ExceptionHandler {

    private val logger = getLogger(ExceptionHandler::class.java)!!

    fun Application.errorHandler() {
        install(StatusPages) {
            exception<BadRequestException> { call, exception ->
                logger.error(exception.message, exception)
                call.respond(HttpStatusCode.BadRequest, mapOf("error" to exception.message))
            }

            exception<RequestValidationException> { call, cause ->
                logger.error(cause.message, cause)
                call.respond(HttpStatusCode.BadRequest, mapOf("error" to cause.reasons.joinToString()))
            }

            exception<IllegalArgumentException> { call, exception ->
                logger.error(exception.message, exception)
                call.respond(HttpStatusCode.BadRequest, mapOf("error" to exception.message))
            }

            exception<Exception> { call, exception ->
                logger.error(exception.message, exception)
                call.respond(HttpStatusCode.InternalServerError, mapOf("error" to exception.message))
            }
        }
    }
}
