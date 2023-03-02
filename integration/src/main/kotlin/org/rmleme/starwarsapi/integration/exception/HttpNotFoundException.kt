package org.rmleme.starwarsapi.integration.exception

class HttpNotFoundException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception()
