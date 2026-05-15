plugins {
    java
    id("war")
    kotlin("jvm") version "2.3.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    // Kotlin standard library
    implementation(kotlin("stdlib"))
    //implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.mindrot:jbcrypt:0.4")
    // Servlet & JSP APIs
    providedCompile("jakarta.servlet:jakarta.servlet-api:6.0.0")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat:2.7.18")
    // JSTL API and Verified Glassfish Core Engine Implementation
    implementation("jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api:3.0.0")
    implementation("org.glassfish.web:jakarta.servlet.jsp.jstl:3.0.1") // Fixe
    // MySQL Connector/J
    implementation("com.mysql:mysql-connector-j:8.1.0")
    //Optimization starts here
    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")
    //REST API
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.16.1")
    //OpenPDF
    implementation("com.github.librepdf:openpdf:1.3.30")
    // Embedded Tomcat Compiler Engine (if running server via Gradle execution)
    implementation("org.apache.tomcat.embed:tomcat-embed-jasper:10.1.19")
    //JSP
    //implementation("org.glassfish.web:jakarta.servlet.jsp:3.0.1")
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<War>{
    webAppDirectory.set(file("src/main/webapp"))
}