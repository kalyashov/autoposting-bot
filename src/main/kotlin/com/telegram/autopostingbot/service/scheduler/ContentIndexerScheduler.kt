package com.telegram.autopostingbot.service.scheduler

import com.telegram.autopostingbot.entity.ContentMetadata
import com.telegram.autopostingbot.entity.SchedulerInfo
import com.telegram.autopostingbot.repository.content.ContentMetadataRepository
import com.telegram.autopostingbot.repository.disk.YandexDiskClient
import com.telegram.autopostingbot.repository.scheduler.SchedulerInfoRepository
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ContentIndexerScheduler(val diskClient: YandexDiskClient,
                              val contentMetadataRepository: ContentMetadataRepository,
                              val schedulerInfoRepository: SchedulerInfoRepository) {

    companion object {
        const val SCHEDULER_NAME = "content_indexer"
        private val logger = LoggerFactory.getLogger(ContentIndexerScheduler::class.java)
    }

    @Scheduled(cron = "\${autoposting-bot.scheduler.content-indexer-cron}")
    fun indexContent() = runBlocking {
        logger.info("Content indexing run")

        val lastScheduledAt = schedulerInfoRepository.findByName(SCHEDULER_NAME)!!.scheduledAt
        val contentMetadataList = diskClient.getImageContentByDate(lastScheduledAt)
            .map { ContentMetadata(it.name, it.path, it.createdAt) }

        contentMetadataList.forEach {
            contentMetadataRepository.save(it)
        }
        // TODO saveAll не работает
        //contentMetadataRepository.saveAll(contentMetadataList)

        schedulerInfoRepository.save(SchedulerInfo(SCHEDULER_NAME, LocalDateTime.now()))

        logger.info("Content indexing done, new content count: ${contentMetadataList.size}")
    }
}