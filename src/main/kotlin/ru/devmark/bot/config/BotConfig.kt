package ru.devmark.bot.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import ru.devmark.bot.service.DevmarkBot

@Configuration
class BotConfig {

    @Bean
    fun telegramBotsApi(devmarkBot: DevmarkBot): TelegramBotsApi =
        TelegramBotsApi(DefaultBotSession::class.java).apply {
            registerBot(devmarkBot)
        }
}