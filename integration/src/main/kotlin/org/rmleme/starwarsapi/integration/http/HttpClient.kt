package org.rmleme.starwarsapi.integration.http

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitStringResponseResult
import kotlinx.coroutines.coroutineScope
import org.rmleme.starwarsapi.integration.exception.HttpNotFoundException
import org.springframework.stereotype.Component
import java.io.IOException
import java.net.HttpURLConnection.HTTP_NOT_FOUND

@Component
class HttpClient {

    suspend fun get(url: String): String = coroutineScope {
        val (request, response, result) = Fuel.get(url).awaitStringResponseResult()

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
}
