package org.rmleme.starwarsapi.httpapi.extension

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

val mapper = jacksonObjectMapper()

internal fun String.asJson(): JsonNode = mapper.readTree(this)
