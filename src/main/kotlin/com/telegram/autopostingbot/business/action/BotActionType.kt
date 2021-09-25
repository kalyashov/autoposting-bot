package com.telegram.autopostingbot.business.action

/**
 * Тип действия
 */
enum class BotActionType {
    /**
     * Отправка сообщения
     */
    SEND_MESSAGE,
    /**
     * Отправка изображения
     */
    SEND_PHOTO
}