plugins {
    java
    id("org.unbroken-dome.xjc") version "2.1.0-SNAPSHOT"
}


dependencies {
    implementation(project(":episode-producer"))
    "xjcEpisodes"(project(":episode-producer"))
}
