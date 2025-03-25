plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "juniordev.com"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	// Spring Security
	implementation("org.springframework.boot:spring-boot-starter-security")

	// JWT (java-jwt)
	implementation("com.auth0:java-jwt:3.18.3")


	implementation("org.flywaydb:flyway-core:11.3.3")
	implementation("org.flywaydb:flyway-mysql:11.3.3")
	implementation("com.h2database:h2:2.2.224")

	implementation("org.springframework.boot:spring-boot-starter-hateoas")

	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("org.mockito:mockito-core:5.8.0")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.16.0")
	runtimeOnly("com.mysql:mysql-connector-j")


	// SpringDoc OpenAPI Starter (Swagger UI)
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.7.0")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
