package com.telegram.autopostingbot.service.admin

import com.telegram.autopostingbot.config.BotConfig
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class AdminServiceImpl(private val botConfig: BotConfig): AdminService {
    override fun isAdmin(update: Update): Boolean {
        return if (update.hasMessage() && update.message.from != null) {
            return botConfig.bot.adminList.contains(update.message.from.id.toString())
        } else {
            false
        }
    }
}