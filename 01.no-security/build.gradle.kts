
plugins {
    java
    `java-library`
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

version = "1.0-SNAPSHOT"

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}


repositories {
    jcenter()
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-web")
    //implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.3.1.RELEASE")
    //implementation("org.springframework.boot:spring-boot-starter-security:2.3.1.RELEASE")
    //implementation("org.springframework.boot:spring-boot-starter-thymeleaf:2.3.1.RELEASE")
    //implementation("org.webjars:bootstrap:4.5.0")
    //implementation("org.webjars:jquery:3.5.1")
    //implementation("com.h2database:h2:1.4.200")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
