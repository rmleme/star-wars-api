dependencies {
    implementation(project(":usecases"))
    implementation(project(":entities"))

    implementation(Dependency.kotlinCoroutinesCore)
    implementation(Dependency.mongoDriver)
    implementation(Dependency.springMongo)

    testImplementation(project(":entities", "test"))
}
