package com.telegram.autopostingbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableR2dbcRepositories
@EnableTransactionManagement
@EnableScheduling
@SpringBootApplication
class AutopostingBotApplication

fun main(args: Array<String>) {
    runApplication<AutopostingBotApplication>(*args)
}
