import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URI

plugins {
	id("org.springframework.boot") version "2.3.1.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72"
}


repositories {
	mavenCentral()
}

group = "io.github.abitgen"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

subprojects{
	repositories {
		mavenCentral()
		jcenter()
		maven {
			url = URI.create("http://repo.maven.apache.org/maven2")
		}
	}
	dependencies {
		apply(plugin = "org.jetbrains.kotlin.jvm")
		implementation(kotlin("stdlib-jdk8"))
		testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
		testImplementation( "org.springframework.boot:spring-boot-starter-test:2.3.1.RELEASE")
	}
	
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
