package com.telegram.autopostingbot.repository

import com.telegram.autopostingbot.repository.entity.ContentMetadata

interface ContentMetadataRepository {

    suspend fun getActual(): ContentMetadata
}