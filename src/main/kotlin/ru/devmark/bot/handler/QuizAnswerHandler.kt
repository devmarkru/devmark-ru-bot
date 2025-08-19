package ru.devmark.bot.handler

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.generics.TelegramClient
import ru.devmark.bot.model.HandlerName
import ru.devmark.bot.util.createMessage
import ru.devmark.bot.util.getInlineKeyboard

@Component
class QuizAnswerHandler : CallbackHandler {

    override val name: HandlerName = HandlerName.QUIZ_ANSWER

    override fun processCallbackData(
        telegramClient: TelegramClient,
        callbackQuery: CallbackQuery,
        arguments: List<String>
    ) {

        val chatId = callbackQuery.message.chatId.toString()

        telegramClient.execute(
            EditMessageReplyMarkup.builder()
                .chatId(chatId)
                .messageId(callbackQuery.message.messageId)
                .inlineMessageId(callbackQuery.inlineMessageId)
                .replyMarkup(getInlineKeyboard(emptyList()))
                .build()
        )

        if (arguments.first() == "b") {
            telegramClient.execute(createMessage(chatId, "Абсолютно верно!"))
        } else {
            telegramClient.execute(createMessage(chatId, "К сожалению, Вы ошиблись..."))
        }
    }
}