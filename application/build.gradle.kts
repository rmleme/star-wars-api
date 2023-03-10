plugins {
    application
}

application {
    mainClass.set("org.rmleme.starwarsapi.application.BootKt")

    applicationDefaultJvmArgs = listOf(
        "-server",
        "-XX:+UseNUMA",
        "-XX:+UseG1GC",
        "-XX:+UseStringDeduplication",
        "-Duser.timezone=UTC"
    )
}

dependencies{
    implementation(project(":entities"))
    implementation(project(":http-api"))
    implementation(project(":integration"))
    implementation(project(":persistence"))
    implementation(project(":usecases"))

    testImplementation(project(":entities", "test"))
    testImplementation(project(":http-api", "test"))

    testImplementation(Dependency.ktorJackson)
    testImplementation(Dependency.ktorTests)
    testImplementation(Dependency.kotestTestcontainers)
    testImplementation(Dependency.kotestWireMock)
    testImplementation(Dependency.mongoDriver)
    testImplementation(Dependency.mongoTestcontainers)
}
