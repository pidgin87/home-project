plugins {
    java
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
}

group = "com.smirnoff.home.finance.history.impl"

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    implementation(project(":module:eureka-client-extension"))
    implementation(project(":module:graphql-openfeigh-extension"))

    implementation(project(":finance:history-service:history-service-dto"))

    implementation(project(":platform:session-service:session-service-client"))

    implementation(project(":platform:dictionary-service:dictionary-service-dto"))
    implementation(project(":platform:dictionary-service:dictionary-service-client"))

    implementation(project(":finance:product-service:product-service-dto"))
    implementation(project(":finance:product-service:product-service-client"))

    implementation(project(":finance:fund-service:fund-service-dto"))
    implementation(project(":finance:fund-service:fund-service-client"))

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.graphql-java:graphql-java-extended-scalars:${property("graphqlJavaExtendedScalars.version")}")
    implementation("org.springframework.boot:spring-boot-starter-graphql")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
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

    testImplementation(project(":finance:history-service:history-service-dto"))
    testImplementation(project(":finance:history-service:history-service-client"))
    testImplementation("org.springframework.boot:spring-boot-starter-test")
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
