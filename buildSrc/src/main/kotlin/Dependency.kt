object Dependency {

    object Versions {

        // Kotlin
        const val kotlin = "1.8.0"

        // Logs
        const val logback = "1.4.5"

        // Spring
        const val springCommon = "6.0.4"

        // Web
        const val ktor = "2.2.3"

        // Tests
        const val kotest = "5.5.4"
        const val kotestSpring = "1.1.2"
        const val mockk = "1.13.4"

        // Code analysis
        const val detekt = "1.22.0"
    }

    // Logs
    const val logback = "ch.qos.logback:logback-classic:${Versions.logback}"

    // Spring
    const val springContext = "org.springframework:spring-context:${Versions.springCommon}"

    // Web
    const val ktorCore = "io.ktor:ktor-server-core-jvm:${Versions.ktor}"
    const val ktorJackson = "io.ktor:ktor-serialization-jackson-jvm:${Versions.ktor}"
    const val ktorNetty = "io.ktor:ktor-server-netty-jvm:${Versions.ktor}"
    const val ktorPluginServerContentNegotiation = "io.ktor:ktor-server-content-negotiation-jvm:${Versions.ktor}"

    // Tests
    const val kotestCore = "io.kotest:kotest-assertions-core-jvm:${Versions.kotest}"
    const val kotestRunner = "io.kotest:kotest-runner-junit5-jvm:${Versions.kotest}"
    const val kotestSpring = "io.kotest.extensions:kotest-extensions-spring:${Versions.kotestSpring}"
    const val ktorTests = "io.ktor:ktor-server-tests-jvm:${Versions.ktor}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val springTest = "org.springframework:spring-test:${Versions.springCommon}"

    // Code analysis
    const val detekt = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detekt}"
}
