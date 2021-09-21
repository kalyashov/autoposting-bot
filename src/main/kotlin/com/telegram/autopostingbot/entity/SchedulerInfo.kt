package com.telegram.autopostingbot.entity

import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("scheduler_info")
class SchedulerInfo(
    @Id
    val name: String,
    @Column
    val scheduledAt: LocalDateTime
): Persistable<String> {

    override fun getId(): String {
        return name
    }

    override fun isNew(): Boolean {
        return false
    }
}