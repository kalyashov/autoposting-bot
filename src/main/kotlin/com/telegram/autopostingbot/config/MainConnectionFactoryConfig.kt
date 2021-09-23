package com.telegram.autopostingbot.config

import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.autoconfigure.r2dbc.ConnectionFactoryBuilder
import org.springframework.boot.autoconfigure.r2dbc.EmbeddedDatabaseConnection
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MainConnectionFactoryConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.r2dbc")
    fun mainR2dbcProperties() = R2dbcProperties()

    @Bean
    fun mainConnectionFactory(mainR2dbcProperties: R2dbcProperties): ConnectionFactory =
        ConnectionFactoryBuilder.of(mainR2dbcProperties) { EmbeddedDatabaseConnection.NONE }!!.build()

}