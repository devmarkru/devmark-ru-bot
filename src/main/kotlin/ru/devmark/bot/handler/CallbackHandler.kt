package ru.devmark.bot.handler

import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.generics.TelegramClient
import ru.devmark.bot.model.HandlerName

interface CallbackHandler {

    val name: HandlerName

    fun processCallbackData(telegramClient: TelegramClient, callbackQuery: CallbackQuery, arguments: List<String>)
}