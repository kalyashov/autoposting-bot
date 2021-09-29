package com.telegram.autopostingbot.repository.content

import com.telegram.autopostingbot.entity.ContentMetadata
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface ContentMetadataRepository: CoroutineCrudRepository<ContentMetadata, String> {

    @Query("select * from content_metadata order by posted_at desc limit 1")
    suspend fun getActual(): ContentMetadata
}