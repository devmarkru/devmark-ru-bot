package ru.devmark.bot.util

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow

fun createMessage(chatId: String, text: String) =
    SendMessage(chatId, text)
        .apply { enableMarkdown(true) }
        .apply { disableWebPagePreview() }

fun createMessageWithSimpleButtons(chatId: String, text: String, simpleButtons: List<List<String>>) =
    createMessage(chatId, text)
        .apply {
            replyMarkup = getSimpleKeyboard(simpleButtons)
        }

fun createMessageWithInlineButtons(chatId: String, text: String, inlineButtons: List<List<Pair<String, String>>>) =
    createMessage(chatId, text)
        .apply {
            replyMarkup = getInlineKeyboard(inlineButtons)
        }

fun getSimpleKeyboard(allButtons: List<List<String>>): ReplyKeyboard =
    ReplyKeyboardMarkup.builder()
        .keyboard(
            allButtons.map { rowButtons ->
                KeyboardRow().apply { rowButtons.forEach { add(it) } }
            }
        )
        .oneTimeKeyboard(true)
        .build()

fun getInlineKeyboard(allButtons: List<List<Pair<String, String>>>): InlineKeyboardMarkup =
    InlineKeyboardMarkup.builder()
        .keyboard(
            allButtons.map { rowButtons ->
                InlineKeyboardRow().apply {
                    rowButtons.forEach { (data, buttonText) ->
                        add(
                            InlineKeyboardButton.builder()
                                .text(buttonText)
                                .callbackData(data)
                                .build()
                        )
                    }
                }
            }
        )
        .build()