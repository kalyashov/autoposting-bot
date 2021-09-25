package com.telegram.autopostingbot.business.action

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.objects.InputFile

/**
 * Действие - отправка изображения
 */
class SendPhotoAction(override val action: SendPhoto) : BotAction<SendPhoto> {
    constructor(channelId: String, inputFile: InputFile) : this(SendPhoto(channelId, inputFile))

    override val actionType: BotActionType
        get() = BotActionType.SEND_PHOTO
}