
plugins {
    java
    `java-library`
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}


repositories {
    jcenter()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
