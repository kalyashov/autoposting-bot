package com.telegram.autopostingbot.service.command.handler

import com.telegram.autopostingbot.business.action.BotAction
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message


interface CommandHandler<T : PartialBotApiMethod<*>?> {
    suspend fun handle(message: Message): List<BotAction<*>>
}
