dependencies {
    implementation(project(":usecases"))
    implementation(project(":entities"))

    testImplementation(project(":entities", "test"))
}
