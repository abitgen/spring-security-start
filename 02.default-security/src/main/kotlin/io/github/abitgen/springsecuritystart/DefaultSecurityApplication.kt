package io.github.abitgen.springsecuritystart

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DefaultSecurityApplication

fun main(args: Array<String>) {
	runApplication<DefaultSecurityApplication>(*args)
}
