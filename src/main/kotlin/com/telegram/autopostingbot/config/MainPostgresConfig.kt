package com.telegram.autopostingbot.config

import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
class MainPostgresConfig(
    private val mainConnectionFactory: ConnectionFactory
) : AbstractR2dbcConfiguration() {

    override fun connectionFactory() = mainConnectionFactory
}