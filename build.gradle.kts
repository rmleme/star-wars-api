import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Dependency.Versions.kotlin
    id("io.gitlab.arturbosch.detekt") version Dependency.Versions.detekt
}

allprojects {
    repositories {
        mavenCentral()
    }

    group = "org.rmleme.starwarsapi"
    version = "1.0.0"
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "io.gitlab.arturbosch.detekt")

    detekt {
        autoCorrect = true
    }

    dependencies {
        implementation(Dependency.logback)

        testImplementation(Dependency.kotestCore)
        testImplementation(Dependency.kotestRunner)
        testImplementation(Dependency.mockk)

        detektPlugins(Dependency.detekt)
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "${JavaVersion.VERSION_17}"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
