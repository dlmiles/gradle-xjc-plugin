plugins {
    java
    id("org.unbroken-dome.xjc") version "2.3.0-SNAPSHOT"
}


repositories {
    mavenCentral()
}


xjc {
    extraArgs.addAll("-Xannotate")
    extension.set(true)
}


dependencies {
    implementation("javax.xml.bind:jaxb-api:2.3.1")

    // Picked 2.0.9 for middling Java8/XJC2.3 support but if you research and fixup your
    // environment and dependencies here you could go newer or older with Java/XJC.
    // -Xannotate: means you need to add dependency:
    xjcClasspath("org.jvnet.jaxb:jaxb2-basics:2.0.9")
    //xjcClasspath("org.jvnet.jaxb:jaxb-annox:2.0.9")
    xjcClasspath("org.jvnet.jaxb:jaxb-basics-annotate:2.0.9")

    // Don't forget to include your implementation dependencies for the annotations themselves
    // A random arbitrary annotation library to demonstrate use, replace with the ones you described in XJB
    implementation("javax.validation:validation-api:2.0.1.Final")
}
