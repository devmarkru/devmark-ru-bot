package ru.devmark.bot.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "telegram")
data class TelegramConfig(
    val token: String,
    val botName: String,
)
