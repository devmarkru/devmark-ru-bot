package ru.devmark.bot.command

import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.chat.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.generics.TelegramClient
import ru.devmark.bot.model.CommandName
import ru.devmark.bot.util.createMessage

@Component
class StartCommand : BotCommand(CommandName.START.text, "") {

    override fun execute(telegramClient: TelegramClient, user: User, chat: Chat, arguments: Array<out String>) {
        telegramClient.execute(createMessage(chat.id.toString(), "Добро пожаловать!"))
    }
}