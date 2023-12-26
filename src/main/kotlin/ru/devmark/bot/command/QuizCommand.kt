package ru.devmark.bot.command

import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender
import ru.devmark.bot.model.HandlerName
import ru.devmark.bot.model.CommandName
import ru.devmark.bot.util.createMessageWithInlineButtons

@Component
class QuizCommand: BotCommand(CommandName.QUIZ.text, "") {

    override fun execute(absSender: AbsSender, user: User, chat: Chat, arguments: Array<out String>) {
        val callback = HandlerName.QUIZ_ANSWER.text
        absSender.execute(
            createMessageWithInlineButtons(
                chat.id.toString(),
                "Как называется ближайшая к Солнцу планета?",
                listOf(
                    listOf("$callback|a" to "Земля", "$callback|b" to "Меркурий"),
                    listOf("$callback|c" to "Плутон", "$callback|d" to "Юпитер"),
                )
            )
        )
    }
}