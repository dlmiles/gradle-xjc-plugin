plugins {
    java
    id("org.unbroken-dome.xjc") version "2.3.0-SNAPSHOT"
}


repositories {
    mavenCentral()
}


dependencies {
    // 2.3 is the default xjcVersion
    implementation("javax.xml.bind:jaxb-api:2.3.1")
}
