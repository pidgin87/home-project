plugins {
    java
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.smirnoff.home.platform.backup"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation("org.apache.commons:commons-lang3:${property("commonsLang3.version")}")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.mapstruct:mapstruct:${property("mapStruct.version")}")
    implementation("net.logstash.logback:logstash-logback-encoder:${property("logstashLogbackEncoder.version")}")

    //    Camel
    implementation("org.apache.camel.springboot:camel-spring-boot:${property("camel.version")}")
    implementation("org.apache.camel.springboot:camel-mapstruct-starter:${property("camel.version")}")
    implementation("org.apache.camel:camel-direct:${property("camel.version")}")
    implementation("org.apache.camel:camel-log:${property("camel.version")}")
    implementation("org.apache.camel:camel-kafka:${property("camel.version")}")
    implementation("org.apache.camel:camel-jsonpath:${property("camel.version")}")
    implementation("org.apache.camel:camel-jackson:${property("camel.version")}")

    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:${property("lombokMapStructProcessor.version")}")
    annotationProcessor("org.mapstruct:mapstruct-processor:${property("mapStruct.version")}")


    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloud.version")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}