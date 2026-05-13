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
    implementation("org.mindrot:jbcrypt:0.4")
    providedCompile("jakarta.servlet:jakarta.servlet-api:6.0.0")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat:2.7.18")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("com.mysql:mysql-connector-j:8.1.0")
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