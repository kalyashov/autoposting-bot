package com.telegram.autopostingbot.business.command

/**
 * Список команд, которые умеет обрабатывать бот
 */
enum class BotCommand(private val commandRaw: String) {
    START("/start"),
    STOP("/stop"),
    UNKNOWN("");

    companion object {
        fun fromValue(commandRaw: String): BotCommand {
            for (command in values()) {
                if (commandRaw.contains(command.commandRaw)) {
                    return command
                }
            }
            return UNKNOWN
        }
    }
}
