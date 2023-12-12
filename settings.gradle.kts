pluginManagement {

    val kotlinVersion: String by settings
    val testSetsVersion: String by settings

    // Still needed while we support building this project before Gradle 7.6
    resolutionStrategy.eachPlugin {
        if (requested.id.namespace == "org.jetbrains.kotlin") {
            useVersion(kotlinVersion)
        }
        if (requested.id.id == "org.unbroken-dome.test-sets") {
            useVersion(testSetsVersion)
        }
        if (requested.id.id == "org.darrylmiles.repack.org.unbroken-dome.test-sets") {
            useVersion(testSetsVersion)
        }
    }

    repositories {
        gradlePluginPortal()
        maven {
            url = uri("https://maven.pkg.github.com/dlmiles/gradle-testsets-plugin")
            content {
                // this repository only contains artifacts for specific groups
                includeGroup("org.darrylmiles.repack.org.unbroken-dome.test-sets")
                includeGroup("org.darrylmiles.repack.org.unbroken-dome")
            }
            credentials {
                username = System.getenv("GITHUB_USERNAME")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
        mavenCentral()
    }
}

rootProject.name = "gradle-xjc-plugin"

if(System.getProperty("excludeDocsTasks") == null) {
    include(":docs")
} else {
    logger.warn("systemProperty \"excludeDocsTasks\" is present, causing project :docs exclusion")
}
