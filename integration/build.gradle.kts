dependencies {
    implementation(project(":usecases"))
    implementation(project(":entities"))

    implementation(Dependency.fuel)
    implementation(Dependency.fuelCoroutines)
    implementation(Dependency.jackson)
    implementation(Dependency.kotlinCoroutinesCore)

    testImplementation(project(":entities", "test"))

    testImplementation(Dependency.kotestWireMock)
}
