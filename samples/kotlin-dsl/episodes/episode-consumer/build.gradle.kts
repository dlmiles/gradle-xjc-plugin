plugins {
    java
    id("org.unbroken-dome.xjc") version "2.2.0"
}


dependencies {
    implementation(project(":episode-producer"))
    "xjcEpisodes"(project(":episode-producer"))
}
