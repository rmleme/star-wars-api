import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Dependency.Versions.kotlin
    id("io.gitlab.arturbosch.detekt") version Dependency.Versions.detekt
    id("org.jetbrains.kotlin.plugin.spring") version Dependency.Versions.kotlin
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
    apply(plugin = "kotlin-spring")
    apply(plugin = "io.gitlab.arturbosch.detekt")

    detekt {
        autoCorrect = true
    }

    dependencies {
        implementation(Dependency.logback)
        implementation(Dependency.springContext)

        testImplementation(Dependency.kotestCore)
        testImplementation(Dependency.kotestRunner)
        testImplementation(Dependency.kotestSpring)
        testImplementation(Dependency.mockk)
        testImplementation(Dependency.springTest)

        detektPlugins(Dependency.detekt)
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "${JavaVersion.VERSION_17}"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            events = setOf(SKIPPED, PASSED, FAILED)
        }
    }
}
