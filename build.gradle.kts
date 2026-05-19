buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        // Enforces uniform alignment across internal compiler tools
        constraints {
            classpath("org.jetbrains.kotlin:kotlin-compiler-embeddable:2.1.10")
        }
    }
}

plugins {
    java
    id("war")
    // Aligned stable Kotlin toolchain runtime
    kotlin("jvm") version "2.1.10"
    id("gg.jte.gradle") version "3.1.13"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin Ecosystem
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlin:kotlin-reflect:2.1.10")
    
    // Security
    implementation("org.mindrot:jbcrypt:0.4")
    
    // Servlet, JSP, & JSTL APIs (Jakarta EE 10 Stack)
    providedCompile("jakarta.servlet:jakarta.servlet-api:6.0.0")
    implementation("jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api:3.0.0")
    implementation("org.glassfish.web:jakarta.servlet.jsp.jstl:3.0.1") 
    
    // Embedded Containers / Web Runtimes
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat:3.2.5")
    implementation("org.apache.tomcat.embed:tomcat-embed-jasper:10.1.19")
    
    // Database Infrastructure
    implementation("com.mysql:mysql-connector-j:8.1.0")
    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")
    
    // JSON Serialization
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.16.1")
    
    // Reporting PDF/Excel
    implementation("com.github.librepdf:openpdf:1.3.30")
    implementation("org.apache.poi:poi-ooxml:5.3.0")
    
    // JTE (Java Template Engine)
    implementation("gg.jte:jte:3.1.13")
    implementation("gg.jte:jte-kotlin:3.1.13")
    implementation("gg.jte:jte-runtime:3.1.13") 
}

kotlin {
    jvmToolchain(21)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<War> {
    webAppDirectory.set(file("src/main/webapp"))
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
    }
}

jte {
    // FIX: Using type-safe .set() layout expecting a java.nio.file.Path target 
    sourceDirectory.set(file("src/main/webapp/WEB-INF/templates").toPath())
    precompile() 
    kotlin {
        packageName.set("gg.jte.generated")
    }
}
