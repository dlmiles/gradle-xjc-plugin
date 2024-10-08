import org.jetbrains.dokka.gradle.DokkaTask
import org.asciidoctor.gradle.jvm.AsciidoctorTask

plugins {
    kotlin("jvm")
    // going to 4.0.0+ forces JDK11+ for doc production
    id("org.asciidoctor.jvm.convert") version "3.3.2"
    id("org.jetbrains.dokka") version "0.10.1"
}

val kotlinVersion: String by extra


repositories {
    mavenCentral()
}


fun resolveSystemGetenv(name: String, defaultValue: String? = null): String? {
    if(System.getenv().containsKey(name))
        return System.getenv(name)
    return defaultValue
}

val githubRepositoryOwner = resolveSystemGetenv("GITHUB_REPOSITORY_OWNER", "unbroken-dome")


tasks.named("dokka", org.jetbrains.dokka.gradle.DokkaTask::class) {
//tasks.withType<org.jetbrains.dokka.gradle.DokkaTask>().configureEach {
    outputFormat = "html"
    configuration {
        externalDocumentationLink {
            url = uri("https://docs.gradle.org/current/javadoc/").toURL()
            // Gradle before 8.8 has package-list
            // Gradle since 8.8 has element-list
            // The upstream Gradle docs for "current" are not being published using JDK11 javadoc which changes the
            // URL filename from "package-list" to "element-list" since this is an external resource
            packageListUrl = uri("https://docs.gradle.org/current/javadoc/element-list").toURL()
        }
        reportUndocumented = false
        sourceLink {
            path = "src/main/kotlin"
            url = "https://github.com/${githubRepositoryOwner}/gradle-xjc-plugin/blob/v${project.version}/src/main/kotlin"
            lineSuffix = "#L"
        }
        perPackageOption {
            prefix = "org.unbrokendome.gradle.plugins.xjc.internal"
            suppress = true
        }
    }
}


dependencies {
    runtimeOnly("com.bmuschko:asciidoctorj-tabbed-code-extension:0.3")
}


tasks.named("asciidoctor", org.asciidoctor.gradle.jvm.AsciidoctorTask::class) {
//tasks.withType<org.asciidoctor.gradle.jvm.AsciidoctorTask>().configureEach {
    asciidoctorj {
        // going to 3.0.0+ forces JDK11+ for doc production
        setVersion("2.5.13")
    }

    setSourceDir(file("."))
    sources(delegateClosureOf<PatternSet> { include("index.adoc") })

    options(mapOf(
        "doctype" to "book"
    ))
    attributes(mapOf(
        "GITHUB_REPOSITORY_OWNER" to githubRepositoryOwner,
        "github-pages-uri" to "https://${githubRepositoryOwner}.github.io/gradle-xjc-plugin",
        "github-uri" to "https://github.com/${githubRepositoryOwner}/gradle-xjc-plugin",
        "project-version" to project.version,
        "plugin-id" to project.properties["pluginIdForDocs"].toString(),
        "current-release-version" to project.properties["versionForDocs"].toString(),
        "source-highlighter" to "prettify"
    ))
}
