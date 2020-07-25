
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
    implementation(project(":01.no-security"))
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    api("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.webjars:bootstrap:4.5.0")
    implementation("org.webjars:jquery:3.5.1")
    implementation("com.h2database:h2:1.4.200")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
