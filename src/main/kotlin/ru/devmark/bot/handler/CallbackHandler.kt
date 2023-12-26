package ru.devmark.bot.handler

import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.bots.AbsSender
import ru.devmark.bot.model.HandlerName

interface CallbackHandler {

    val name: HandlerName

    fun processCallbackData(absSender: AbsSender, callbackQuery: CallbackQuery, arguments: List<String>)
}