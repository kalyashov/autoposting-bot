package com.telegram.autopostingbot.business.action

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod

/**
 * Действие, которое необходимо выполнить боту
 */
interface BotAction<T : PartialBotApiMethod<*>?> {

    /**
     * @return тип действия
     */
    val actionType: BotActionType

    /**
     * @return действие
     */
    val action: T
}
