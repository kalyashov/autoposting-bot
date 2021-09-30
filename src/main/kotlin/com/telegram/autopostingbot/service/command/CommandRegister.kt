package com.telegram.autopostingbot.service.command

import com.telegram.autopostingbot.business.command.BotCommand
import com.telegram.autopostingbot.service.command.handler.*
import org.springframework.stereotype.Service
import java.util.*

@Service
class CommandRegister(
    startCommandHandler: StartCommandHandler,
    stopCommandHandler: StopCommandHandler,
    unknownCommandHandler: UnknownCommandHandler) {

    private final val botCommands: MutableMap<BotCommand, CommandHandler<*>> = EnumMap(BotCommand::class.java)

    fun getHandlerByCommand(command: BotCommand): CommandHandler<*> {
        return botCommands[command]!!
    }

    init {
        botCommands[BotCommand.START] = startCommandHandler
        botCommands[BotCommand.STOP] = stopCommandHandler
        botCommands[BotCommand.UNKNOWN] = unknownCommandHandler
    }
}
