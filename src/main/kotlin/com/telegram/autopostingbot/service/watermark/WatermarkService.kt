package com.telegram.autopostingbot.service.watermark

import java.awt.image.BufferedImage

/**
 * Сервис для добавления "водяных" знаков на изображения
 */
interface WatermarkService {

    fun addWatermark(originalImg: BufferedImage): BufferedImage
}