package com.telegram.autopostingbot.service.scheduler

import com.telegram.autopostingbot.AutopostingBot
import com.telegram.autopostingbot.repository.content.ContentMetadataRepository
import com.telegram.autopostingbot.repository.disk.YandexDiskClient
import com.telegram.autopostingbot.service.ImagePostPrepareService
import kotlinx.coroutines.runBlocking
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ChannelAutopostingScheduler(
    val contentMetadataRepository: ContentMetadataRepository,
    val diskClient: YandexDiskClient,
    val imagePostPrepareService: ImagePostPrepareService,
    val bot: AutopostingBot
) {

    @Scheduled(cron = "\${autoposting-bot.scheduler.post-content-cron}")
    fun postContent() = runBlocking {
        val contentMetadata = contentMetadataRepository.getActual()
        val img = diskClient.getImageByPath(contentMetadata.path)

        val sendPhotoAction = imagePostPrepareService.prepareSendImageAction(img, contentMetadata.name)
        bot.processAction(sendPhotoAction)

        contentMetadata.postedAt = LocalDateTime.now()
        contentMetadataRepository.save(contentMetadata)
    }
}