plugins {
    java
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
}

group = "com.smirnoff.home.finance.rate.procedure"

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    implementation(project(":module:graphql-openfeigh-extension"))

//    dictionary
    implementation(project(":platform:dictionary-service:dictionary-service-dto"))
    implementation(project(":platform:dictionary-service:dictionary-service-client"))

//    spring batch
    implementation("org.springframework.boot:spring-boot-starter-batch")

//    spring integration
    implementation("org.springframework.boot:spring-boot-starter-integration")
    implementation("org.springframework.integration:spring-integration-jpa")
    implementation("org.springframework.integration:spring-integration-http")
    implementation("org.apache.tomcat.embed:tomcat-embed-core")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-graphql")

    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:${property("springCloud.openFeign.version")}")

    implementation("org.mapstruct:mapstruct:${property("mapStruct.version")}")
    implementation("net.logstash.logback:logstash-logback-encoder:${property("logstashLogbackEncoder.version")}")

    compileOnly("org.projectlombok:lombok")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:${property("lombokMapStructProcessor.version")}")
    annotationProcessor("org.mapstruct:mapstruct-processor:${property("mapStruct.version")}")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework:spring-webflux")
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
