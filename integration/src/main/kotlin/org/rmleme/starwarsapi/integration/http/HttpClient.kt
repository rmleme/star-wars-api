package org.rmleme.starwarsapi.integration.http

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitStringResponseResult
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class HttpClient {

    suspend fun get(url: String): String = coroutineScope {
        val (request, response, result) = Fuel.get(url).awaitStringResponseResult()

        result.fold(
            success = {
                it
            },
            failure = {
                throw IOException(
                    "Error calling ${request.method} ${request.url} (status code = ${response.statusCode})",
                    it.exception
                )
            }
        )
    }
}
