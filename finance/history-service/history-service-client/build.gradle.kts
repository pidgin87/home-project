plugins {
    java
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.smirnoff.home.finance.history.client"
version = "0.0.1-SNAPSHOT"

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    implementation(project(":module:eureka-client-extension"))
    implementation(project(":module:graphql-openfeigh-extension"))
    implementation(project(":finance:history-service:history-service-dto"))

    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:${property("springCloud.openFeign.version")}")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:${property("lombokMapStructProcessor.version")}")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.graphql:spring-graphql-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloud.version")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.bootJar {
    enabled = false
}
