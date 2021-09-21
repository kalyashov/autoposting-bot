package com.telegram.autopostingbot.entity

import java.time.LocalDateTime

data class ContentInfo(
    val name: String,
    val path: String,
    val createdAt: LocalDateTime)
