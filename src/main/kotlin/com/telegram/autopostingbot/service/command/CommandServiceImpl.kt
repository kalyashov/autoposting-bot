package com.telegram.autopostingbot.service.command

import com.telegram.autopostingbot.business.action.BotAction
import com.telegram.autopostingbot.business.command.BotCommand
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message

@Service
class CommandServiceImpl(val commandRegister: CommandRegister): CommandService {

    override fun handleCommand(message: Message): List<BotAction<*>> {
        val command = BotCommand.fromValue(message.text)
        return commandRegister.getHandlerByCommand(command).handle(message)
    }

}