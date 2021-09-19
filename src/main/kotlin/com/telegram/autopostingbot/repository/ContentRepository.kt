package com.telegram.autopostingbot.repository

import com.telegram.autopostingbot.repository.entity.Content

interface ContentRepository {

    suspend fun getById(id: String): Content

    suspend fun getAll(): List<Content>
}