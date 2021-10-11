package com.telegram.autopostingbot.service.command.handler

import com.telegram.autopostingbot.business.action.BotAction
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class StartCommandHandler: CommandHandler<SendMessage> {

    override suspend fun handle(message: Message): List<BotAction<*>> {
        TODO("Not yet implemented")
    }
}