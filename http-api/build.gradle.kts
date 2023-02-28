dependencies {
    implementation(project(":entities"))
    implementation(project(":usecases"))

    implementation(Dependency.ktorCore)
    implementation(Dependency.ktorJackson)
    implementation(Dependency.ktorNetty)
    implementation(Dependency.ktorPluginClientContentNegotiation)
    implementation(Dependency.ktorPluginServerContentNegotiation)

    testImplementation(Dependency.ktorTests)
}