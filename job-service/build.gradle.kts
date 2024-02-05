plugins {
    java
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.smirnoff.home.garden.iot"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("net.logstash.logback:logstash-logback-encoder:${property("logstashLogbackEncoder.version")}")

    implementation("org.apache.camel.springboot:camel-spring-boot:${property("camel.version")}")
    implementation("org.apache.camel:camel-timer:${property("camel.version")}")
    implementation("org.apache.camel:camel-log:${property("camel.version")}")
    implementation("org.apache.camel:camel-graphql:${property("camel.version")}")
    implementation("org.apache.camel:camel-jsonpath:${property("camel.version")}")
    implementation("org.apache.camel:camel-direct:${property("camel.version")}")

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