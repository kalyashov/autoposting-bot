package com.telegram.autopostingbot.business.action

import org.telegram.telegrambots.meta.api.methods.send.SendMessage

/**
 * Действие - отправка сообщения
 */
class SendMessageAction(override val action: SendMessage) : BotAction<SendMessage> {
    override val actionType: BotActionType
        get() = BotActionType.SEND_MESSAGE
}