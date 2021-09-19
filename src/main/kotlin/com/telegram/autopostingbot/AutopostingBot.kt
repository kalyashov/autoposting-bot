package com.telegram.autopostingbot

import com.telegram.autopostingbot.config.BotConfig
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class AutopostingBot(private val botConfig: BotConfig): TelegramLongPollingBot() {
    companion object {
        private val logger = LoggerFactory.getLogger(AutopostingBot::class.java)
    }

    override fun getBotUsername(): String {
        return botConfig.name
    }

    override fun getBotToken(): String {
        return botConfig.token
    }

    override fun onUpdateReceived(update: Update) {
        logger.info(update.toString())
    }
}
