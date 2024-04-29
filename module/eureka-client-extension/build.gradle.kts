plugins {
    java
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.smirnoff.home.platform.module.eureka-client-extension"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation("org.springframework:spring-context")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloud.version")}")
    }
}

tasks.bootJar {
    enabled = false
}