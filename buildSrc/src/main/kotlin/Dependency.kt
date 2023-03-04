object Dependency {

    object Versions {

        // Kotlin
        const val kotlin = "1.8.0"
        const val kotlinCoroutine = "1.6.4"

        // Logs
        const val logback = "1.4.5"

        // Json
        const val jackson = "2.14.2"

        // Spring
        const val springCommon = "6.0.4"

        // Web
        const val fuel = "2.3.1"
        const val ktor = "2.2.3"

        // Tests
        const val kotest = "5.5.4"
        const val kotestSpring = "1.1.2"
        const val kotestWireMock = "1.0.3"
        const val mockk = "1.13.4"

        // Code analysis
        const val detekt = "1.22.0"
        const val jacoco = "0.8.8"
    }

    // Kotlin
    const val kotlinCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:${Versions.kotlinCoroutine}"

    // Logs
    const val logback = "ch.qos.logback:logback-classic:${Versions.logback}"

    // Json
    const val jackson = "com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jackson}"

    // Spring
    const val springContext = "org.springframework:spring-context:${Versions.springCommon}"

    // Web
    const val fuel = "com.github.kittinunf.fuel:fuel:${Versions.fuel}"
    const val fuelCoroutines = "com.github.kittinunf.fuel:fuel-coroutines:${Versions.fuel}"
    const val ktorCore = "io.ktor:ktor-server-core-jvm:${Versions.ktor}"
    const val ktorJackson = "io.ktor:ktor-serialization-jackson-jvm:${Versions.ktor}"
    const val ktorNetty = "io.ktor:ktor-server-netty-jvm:${Versions.ktor}"
    const val ktorPluginClientContentNegotiation = "io.ktor:ktor-client-content-negotiation-jvm:${Versions.ktor}"
    const val ktorPluginServerContentNegotiation = "io.ktor:ktor-server-content-negotiation-jvm:${Versions.ktor}"
    const val ktorPluginServerRequestValidation = "io.ktor:ktor-server-request-validation-jvm:${Versions.ktor}"
    const val ktorPluginServerStatusPages = "io.ktor:ktor-server-status-pages-jvm:${Versions.ktor}"

    // Tests
    const val kotestCore = "io.kotest:kotest-assertions-core-jvm:${Versions.kotest}"
    const val kotestRunner = "io.kotest:kotest-runner-junit5-jvm:${Versions.kotest}"
    const val kotestSpring = "io.kotest.extensions:kotest-extensions-spring:${Versions.kotestSpring}"
    const val kotestWireMock = "io.kotest.extensions:kotest-extensions-wiremock:${Versions.kotestWireMock}"
    const val ktorTests = "io.ktor:ktor-server-tests-jvm:${Versions.ktor}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val springTest = "org.springframework:spring-test:${Versions.springCommon}"

    // Code analysis
    const val detekt = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detekt}"
}
