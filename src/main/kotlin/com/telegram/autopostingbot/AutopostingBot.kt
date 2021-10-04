package com.telegram.autopostingbot

import com.telegram.autopostingbot.business.action.BotAction
import com.telegram.autopostingbot.business.action.BotActionType
import com.telegram.autopostingbot.config.BotConfig
import com.telegram.autopostingbot.service.ImagePostPrepareService
import com.telegram.autopostingbot.service.admin.AdminService
import com.telegram.autopostingbot.service.message.MessageProcessor
import com.telegram.autopostingbot.service.message.MessageProcessorImpl
import com.telegram.autopostingbot.util.downloadPhoto
import com.telegram.autopostingbot.util.hasMessageWithPhoto
import com.telegram.autopostingbot.util.hasMessageWithText
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class AutopostingBot(private val botConfig: BotConfig,
                     private val adminService: AdminService,
                     private val messageProcessor: MessageProcessor,
                     private val imagePostPrepareService: ImagePostPrepareService)
    : TelegramLongPollingBot() {

    companion object {
        private val logger = LoggerFactory.getLogger(MessageProcessorImpl::class.java)
    }

    override fun getBotUsername(): String {
        return botConfig.bot.name
    }

    override fun getBotToken(): String {
        return botConfig.bot.token
    }

    override fun onUpdateReceived(update: Update) {
        logger.info(update.toString())

        if (adminService.isAdmin(update)) {
            processActions(handleUpdate(update))
        }
    }

    fun processActions(botActions: List<BotAction<*>>) {
        botActions.forEach {
            processAction(it)
        }
    }

    fun processAction(botAction: BotAction<*>) {
        when (botAction.actionType) {
            BotActionType.SEND_MESSAGE -> execute(botAction.action as SendMessage)
            BotActionType.SEND_PHOTO -> execute(botAction.action as SendPhoto)
        }
    }

    private fun handleUpdate(update: Update): List<BotAction<*>> {
        return when {
            update.hasMessageWithPhoto() -> {
                forwardPhotoInChannel(update)
            }
            update.hasMessageWithText() -> {
                messageProcessor.process(update.message)
            }
            else -> {
                emptyList()
            }
        }
    }

    private fun forwardPhotoInChannel(update: Update): List<BotAction<*>> {
        val photoDataToForward = update.message.photo.maxByOrNull { it.fileSize }!!
        val img = downloadPhoto(photoDataToForward)
        return listOf(imagePostPrepareService.prepareSendImageAction(img))
    }
}