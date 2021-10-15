package com.telegram.autopostingbot.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "autoposting-bot")
data class BotConfig(val bot: Bot, val disk: Disk, val watermark: Watermark) {
    data class Bot(val name: String, val token: String, val channelId: String, val adminList: Set<String>)
    data class Disk(val userAgent: String, val token: String, val folder: String, val endpoint: String)
    data class Watermark(val imgSrc: String,
                         val verticalTextSrc: String,
                         val horizontalTextSrc: String,
                         val imgWmMinHeight : Int,
                         val imgOpacity: Float,
                         val textOpacity: Float,
                         val text: String)
}
