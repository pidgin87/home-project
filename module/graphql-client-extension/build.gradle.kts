plugins {
    java
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.smirnoff.home.platform.module.graphql-openfeigh-extension"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation("org.springframework:spring-context")
    implementation("org.springframework.graphql:spring-graphql")
    implementation("org.springframework:spring-web")
    implementation("org.apache.tomcat.embed:tomcat-embed-core")

    implementation(project(":platform:session-service:session-service-client"))

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