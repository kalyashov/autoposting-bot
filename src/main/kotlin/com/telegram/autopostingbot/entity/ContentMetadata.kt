package com.telegram.autopostingbot.entity

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table("content_metadata")
class ContentMetadata(
    @Id
    private var id: UUID?,
    @Column
    var name: String,
    @Column
    var path: String,
    @Column
    var createdAt: LocalDateTime,
    @Column
    var postedAt: LocalDateTime? = null
): Persistable<String> {

    @Transient
    private var isNew = false

    constructor(name: String, path: String, createdAt: LocalDateTime)
            : this(UUID.randomUUID(), name, path, createdAt, null) {
        this.isNew = true
    }

    override fun getId(): String? {
        return id?.toString()
    }

    override fun isNew(): Boolean {
        return isNew
    }
}
