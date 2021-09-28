package com.telegram.autopostingbot.service.admin

import org.telegram.telegrambots.meta.api.objects.Update

/**
 * Сервис для проверки прав пользователя
 */
interface AdminService {
    fun isAdmin(update: Update): Boolean
}