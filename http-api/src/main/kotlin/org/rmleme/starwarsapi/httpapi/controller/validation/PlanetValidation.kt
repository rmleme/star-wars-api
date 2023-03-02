package org.rmleme.starwarsapi.httpapi.controller.validation

import io.ktor.server.plugins.requestvalidation.RequestValidationConfig
import io.ktor.server.plugins.requestvalidation.ValidationResult.Invalid
import io.ktor.server.plugins.requestvalidation.ValidationResult.Valid
import org.rmleme.starwarsapi.httpapi.controller.dto.request.PlanetRequest

fun RequestValidationConfig.validatePlanetRequest() {
    validate<PlanetRequest> {
        when {
            it.id <= 0 -> Invalid("id must be non-null and > 0")
            else -> Valid
        }
    }
}
