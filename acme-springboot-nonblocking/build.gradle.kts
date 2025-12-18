plugins {
    id("org.jetbrains.kotlin.jvm") version "2.2.20"
    id("org.jetbrains.kotlin.plugin.spring") version "2.2.20"
    id("org.springframework.boot") version "4.0.0"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "acme"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot WebFlux (non-blocking)
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // R2DBC for non-blocking database access
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework:spring-jdbc")
    runtimeOnly("org.postgresql:r2dbc-postgresql")

    // Flyway for migrations (still uses JDBC)
    implementation("org.springframework.boot:spring-boot-starter-flyway")
    implementation("org.flywaydb:flyway-database-postgresql")
    runtimeOnly("org.postgresql:postgresql")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    // Jackson Kotlin module for JSON serialization
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // OpenAPI documentation for WebFlux
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:3.0.0")

    // Development
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
