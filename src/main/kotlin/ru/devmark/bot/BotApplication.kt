package ru.devmark.bot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.telegram.telegrambots.ApiContextInitializer

@SpringBootApplication
class BotApplication

fun main(args: Array<String>) {
    ApiContextInitializer.init()
    runApplication<BotApplication>(*args)
}
