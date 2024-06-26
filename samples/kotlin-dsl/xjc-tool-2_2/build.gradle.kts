plugins {
    java
    id("org.unbroken-dome.xjc") version "2.3.0-SNAPSHOT"
}


repositories {
    mavenCentral()
}


xjc {
    xjcVersion.set("2.2")
}


dependencies {
    implementation("javax.xml.bind:jaxb-api:2.2.12")

    xjcTool("javax.xml.bind:jaxb-api:2.2.12")

    xjcTool("com.sun.xml.bind:jaxb-xjc:2.2.11")
    xjcTool("com.sun.xml.bind:jaxb-impl:2.2.11")
    xjcTool("com.sun.xml.bind:jaxb-core:2.2.11")
}
