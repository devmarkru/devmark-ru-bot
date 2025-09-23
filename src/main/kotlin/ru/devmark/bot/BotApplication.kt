package ru.devmark.bot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class BotApplication

fun main(args: Array<String>) {
    runApplication<BotApplication>(*args)
}
