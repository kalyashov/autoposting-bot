package com.telegram.autopostingbot.service.message

import com.telegram.autopostingbot.business.action.BotAction
import org.telegram.telegrambots.meta.api.objects.Message

/**
 * Service for process users messages
 */
interface MessageProcessor {
    fun process(message: Message): List<BotAction<*>>
}