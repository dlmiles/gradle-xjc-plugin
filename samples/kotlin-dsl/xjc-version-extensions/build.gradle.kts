plugins {
    java
    id("org.unbroken-dome.xjc") version "2.3.0-SNAPSHOT"
}


repositories {
    mavenCentral()
}



xjc {
    // -episode: is managed by gradle-xjc-plugin already
    extraArgs.addAll("-Xpropertyaccessors", "-mark-generated", "-Xlocator", "-Xsync-methods", "-Xinject-code")
}


dependencies {
    // 2.3 is the default xjcVersion
    implementation("javax.xml.bind:jaxb-api:2.3.1")

    // -mark-generated: means you need to add dependency:
    implementation("javax.annotation:javax.annotation-api:1.3.2")

    // -Xlocator: means you need to add dependency:
    implementation("com.sun.xml.bind:jaxb-core:2.3.0.1")
}
