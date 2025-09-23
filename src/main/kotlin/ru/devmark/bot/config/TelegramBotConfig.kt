package ru.devmark.bot.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication
import org.telegram.telegrambots.meta.generics.TelegramClient
import ru.devmark.bot.service.DevmarkBot

@Configuration
class TelegramBotConfig {
    @Bean
    fun telegramClient(telegramConfig: TelegramConfig): TelegramClient =
        OkHttpTelegramClient(telegramConfig.token)

    @Bean
    fun telegramBotsLongPollingApplication(
        telegramConfig: TelegramConfig,
        bot: DevmarkBot,
    ): TelegramBotsLongPollingApplication =
        TelegramBotsLongPollingApplication().apply {
            registerBot(telegramConfig.token, bot)
        }
}
