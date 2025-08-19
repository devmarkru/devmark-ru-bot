package ru.devmark.bot.command

import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.chat.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.generics.TelegramClient
import ru.devmark.bot.model.CommandName
import ru.devmark.bot.util.createMessageWithSimpleButtons

@Component
class ButtonsCommand : BotCommand(CommandName.BUTTONS.text, "") {

    override fun execute(telegramClient: TelegramClient, user: User, chat: Chat, arguments: Array<out String>) {
        telegramClient.execute(
            createMessageWithSimpleButtons(
                chat.id.toString(),
                "Нажмите на одну из кнопок.",
                listOf(
                    "Кнопка 1",
                    "Кнопка 2",
                    "Кнопка 3",
                    "Кнопка 4",
                ).chunked(2)
            )
        )
    }
}