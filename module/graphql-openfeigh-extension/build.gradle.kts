plugins {
    java
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.smirnoff.home.platform.module.graphql-openfeigh-extension"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-annotations")
    implementation("org.springframework:spring-context")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:${property("springCloud.openFeign.version")}")
    implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer")

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