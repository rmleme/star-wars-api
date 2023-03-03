package org.rmleme.starwarsapi.integration.http

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.coroutines.awaitStringResponseResult
import kotlinx.coroutines.coroutineScope
import org.rmleme.starwarsapi.integration.exception.HttpNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.IOException
import java.net.HttpURLConnection.HTTP_NOT_FOUND

@Component
class HttpClient {

    suspend fun get(url: String): String = coroutineScope {
        val (request, response, result) = Fuel.get(url).awaitStringResponseResult()
        logger.debug("swapi request: ${request.method} ${request.url}")
        logger.debug(
            "swapi response: ${response.statusCode} ${response.url}\n${response.body().asString(
                response.headers[Headers.CONTENT_TYPE].lastOrNull()
            )}"
        )

        result.fold(
            success = { it },
            failure = {
                if (response.statusCode == HTTP_NOT_FOUND) {
                    throw HttpNotFoundException("Requested resource not found for the given URL: $url", it.exception)
                } else {
                    throw IOException(
                        "Error calling ${request.method} ${request.url} (status code = ${response.statusCode})",
                        it.exception
                    )
                }
            }
        )
    }

    private companion object {
        val logger = LoggerFactory.getLogger(HttpClient::class.java)!!
    }
}
