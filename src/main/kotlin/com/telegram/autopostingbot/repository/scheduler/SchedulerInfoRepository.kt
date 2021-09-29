package com.telegram.autopostingbot.repository.scheduler

import com.telegram.autopostingbot.entity.SchedulerInfo
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface SchedulerInfoRepository: CoroutineCrudRepository<SchedulerInfo, String> {

    @Query("select * from scheduler_info where name=:name")
    suspend fun findByName(name: String): SchedulerInfo?
}