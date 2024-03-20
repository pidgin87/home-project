plugins {
    java
}

group = "com.smirnoff.home.garden"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

allprojects {
    repositories {
        mavenCentral()
    }

    extra["springCloud.version"] = "2023.0.0"
    extra["springCloud.openFeign.version"] = "4.1.0"
    extra["camel.version"] = "4.3.0"
    extra["springBoot.version"] = "3.2.2"
    extra["mapStruct.version"] = "1.5.5.Final"
    extra["lombokMapStructProcessor.version"] = "0.2.0"
    extra["logstashLogbackEncoder.version"] = "7.3"
    extra["commonsLang3.version"] = "3.14.0"
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
