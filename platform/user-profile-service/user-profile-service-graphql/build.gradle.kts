plugins {
    java
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.hibernate.orm") version "6.4.1.Final"
}

group = "com.smirnoff.home.platform.user.profile.impl"
version = "0.0.1-SNAPSHOT"

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    implementation(project(":module:eureka-client-extension"))
    implementation(project(":module:graphql-openfeigh-extension"))

    implementation(project(":platform:user-profile-service:user-profile-service-dto"))

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

hibernate {
    enhancement {
        enableAssociationManagement.set(true)
    }
}
