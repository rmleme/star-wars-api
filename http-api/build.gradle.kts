dependencies {
    implementation(project(":entities"))
    implementation(project(":usecases"))

    implementation(Dependency.ktorCore)
    implementation(Dependency.ktorJackson)
    implementation(Dependency.ktorNetty)
    implementation(Dependency.ktorPluginClientContentNegotiation)
    implementation(Dependency.ktorPluginServerContentNegotiation)
    implementation(Dependency.ktorPluginServerRequestValidation)
    implementation(Dependency.ktorPluginServerStatusPages)

    testImplementation(project(":entities", "test"))

    testImplementation(Dependency.ktorTests)
}

configurations {
    create("test")
}

tasks.register<Jar>("testArchive") {
    archiveBaseName.set("http-api-test")
    from(project.the<SourceSetContainer>()["test"].output)
}

artifacts {
    add("test", tasks["testArchive"])
}
