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
    implementation(project(":http-api"))
    implementation(project(":persistence"))
}
