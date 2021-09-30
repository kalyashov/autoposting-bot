package com.telegram.autopostingbot.service.command

import com.telegram.autopostingbot.business.action.BotAction
import org.telegram.telegrambots.meta.api.objects.Message

/**
 * Service to handle commands from users
 */
interface CommandService {
    fun handleCommand(message: Message): List<BotAction<*>>
}