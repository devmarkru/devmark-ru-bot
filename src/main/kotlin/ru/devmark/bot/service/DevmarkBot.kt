package ru.devmark.bot.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient
import org.telegram.telegrambots.extensions.bots.commandbot.CommandLongPollingTelegramBot
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
import org.telegram.telegrambots.meta.api.objects.Update
import ru.devmark.bot.handler.CallbackHandler
import ru.devmark.bot.util.createMessage

@Component
class DevmarkBot(
    commands: Set<BotCommand>,
    callbackHandlers: Set<CallbackHandler>,
    @Value("\${telegram.token}")
    private val token: String,
    @Value("\${telegram.botName}")
    private val botName: String,
) : CommandLongPollingTelegramBot(OkHttpTelegramClient(token), true, { botName }),
    SpringLongPollingBot {

    private val handlerMapping: Map<String, CallbackHandler> =
        callbackHandlers.associateBy { it.name.text }

    init {
        registerAll(*commands.toTypedArray())
    }

    override fun processNonCommandUpdate(update: Update) {
        if (update.hasMessage()) {
            val chatId = update.message.chatId.toString()
            if (update.message.hasText()) {
                telegramClient.execute(createMessage(chatId, "Вы написали: *${update.message.text}*"))
            } else {
                telegramClient.execute(createMessage(chatId, "Я понимаю только текст!"))
            }
        } else if (update.hasCallbackQuery()) {
            val callbackQuery = update.callbackQuery
            val callbackData = callbackQuery.data

            val callbackQueryId = callbackQuery.id
            telegramClient.execute(AnswerCallbackQuery(callbackQueryId))

            val callbackArguments = callbackData.split("|")
            val callbackHandlerName = callbackArguments.first()

            handlerMapping.getValue(callbackHandlerName)
                .processCallbackData(
                    telegramClient,
                    callbackQuery,
                    callbackArguments.subList(1, callbackArguments.size)
                )
        }
    }

    override fun getBotToken(): String = token

    override fun getUpdatesConsumer(): LongPollingUpdateConsumer = this
}
