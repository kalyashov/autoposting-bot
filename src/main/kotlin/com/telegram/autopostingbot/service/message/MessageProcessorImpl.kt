package com.telegram.autopostingbot.service.message

import com.telegram.autopostingbot.business.action.BotAction
import com.telegram.autopostingbot.service.command.CommandService
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message

@Service
class MessageProcessorImpl(private val commandService: CommandService): MessageProcessor {

    override fun process(message: Message): List<BotAction<*>> {
        return  commandService.handleCommand(message)
    }
}