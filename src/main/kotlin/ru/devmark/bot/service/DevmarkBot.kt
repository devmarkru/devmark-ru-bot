package ru.devmark.bot.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow

@Service
class DevmarkBot : TelegramLongPollingBot() {

    @Value("\${telegram.botName}")
    private val botName: String = ""

    @Value("\${telegram.token}")
    private val token: String = ""

    override fun getBotUsername(): String = botName

    override fun getBotToken(): String = token

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage()) {
            val message = update.message
            val chatId = message.chatId
            val responseText = if (message.hasText()) {
                val messageText = message.text
                when {
                    messageText == "/start" -> "Добро пожаловать!"
                    messageText.startsWith("Кнопка ") -> "Вы нажали кнопку"
                    else -> "Вы написали: *$messageText*"
                }
            } else {
                "Я понимаю только текст"
            }
            sendNotification(chatId, responseText)
        }
    }

    private fun sendNotification(chatId: Long, responseText: String) {
        val responseMessage = SendMessage(chatId, responseText)
        responseMessage.setParseMode("Markdown")
        responseMessage.replyMarkup = getReplyMarkup(
                listOf(
                        listOf("Кнопка 1", "Кнопка 2"),
                        listOf("Кнопка 3", "Кнопка 4")
                )
        )
        execute(responseMessage)
    }

    private fun getReplyMarkup(allButtons: List<List<String>>): ReplyKeyboardMarkup {
        val markup = ReplyKeyboardMarkup()
        markup.keyboard = allButtons.map { rowButtons ->
            val row = KeyboardRow()
            rowButtons.forEach { rowButton -> row.add(rowButton) }
            row
        }
        return markup
    }
}