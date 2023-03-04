import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Dependency.Versions.kotlin
    id("io.gitlab.arturbosch.detekt") version Dependency.Versions.detekt
    id("org.jetbrains.kotlin.plugin.spring") version Dependency.Versions.kotlin
    jacoco
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
    apply(plugin = "jacoco")

    detekt {
        autoCorrect = true
    }

    jacoco {
        toolVersion = Dependency.Versions.jacoco
        reportsDirectory.set(file("$buildDir/reports/jacoco"))
    }

    tasks.jacocoTestReport {
        reports {
            xml.required.set(true)
            csv.required.set(false)
            html.required.set(true)
        }
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
            allWarningsAsErrors = true
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            events = setOf(SKIPPED, PASSED, FAILED)
        }
    }
}

tasks.register<JacocoReport>("codeCoverageReport") {
    jacoco { toolVersion = Dependency.Versions.jacoco }
    subprojects.forEach { subproject ->
        subproject.plugins.withType<JacocoPlugin>().configureEach {
            subproject.tasks
                .matching { it.extensions.findByType<JacocoTaskExtension>() != null }
                .configureEach {
                    sourceSets(subproject.sourceSets.main.get())
                    executionData(
                        files(this).filter { it.isFile && it.exists() }
                    )
                }
            subproject.tasks
                .matching { it.extensions.findByType<JacocoTaskExtension>() != null }
                .configureEach { rootProject.tasks["codeCoverageReport"].dependsOn(this) }
            subproject.tasks
                .withType<Test>()
                .configureEach { rootProject.tasks["codeCoverageReport"].dependsOn(this) }
        }
    }
    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.required.set(true)
    }
}
