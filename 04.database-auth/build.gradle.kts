
plugins {
    java
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
    implementation(project(":02.default-security"))
    implementation(project(":lib-roles-permissions"))
}


tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
