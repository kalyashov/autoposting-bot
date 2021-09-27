package com.telegram.autopostingbot.util

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.GetFile
import org.telegram.telegrambots.meta.api.objects.PhotoSize
import java.io.File

fun TelegramLongPollingBot.downloadPhoto(photo: PhotoSize): File {
    val filePath = getFilePath(photo)
    return downloadFile(filePath)
}

fun TelegramLongPollingBot.getFilePath(photo: PhotoSize): String {
    return if (!photo.filePath.isNullOrBlank()) {
        photo.filePath
    } else {
        val getFileMethod = GetFile()
        getFileMethod.fileId = photo.fileId

        val file  = execute(getFileMethod)
        file.filePath
    }
}

