package com.telegram.autopostingbot.service.command.handler

import com.telegram.autopostingbot.business.action.BotAction
import com.telegram.autopostingbot.repository.content.ContentMetadataRepository
import com.telegram.autopostingbot.repository.disk.YandexDiskClient
import com.telegram.autopostingbot.service.ImagePostPrepareService
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import java.time.LocalDateTime

@Service
class NextPostCommandHandler(val contentMetadataRepository: ContentMetadataRepository,
                             val diskClient: YandexDiskClient,
                             val imagePostPrepareService: ImagePostPrepareService)
    : CommandHandler<SendMessage> {

    override suspend fun handle(message: Message): List<BotAction<*>> {
        val contentMetadata = contentMetadataRepository.getActual()
        val img = diskClient.getImageByPath(contentMetadata.path)

        val sendPhotoAction = imagePostPrepareService.prepareSendImageAction(img, contentMetadata.name)

        contentMetadata.postedAt = LocalDateTime.now()
        contentMetadataRepository.save(contentMetadata)

        return listOf(sendPhotoAction)
    }
}