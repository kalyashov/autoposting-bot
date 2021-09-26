package com.telegram.autopostingbot.service.watermark

import com.telegram.autopostingbot.config.BotConfig
import net.coobird.thumbnailator.Thumbnails
import net.coobird.thumbnailator.filters.Watermark
import net.coobird.thumbnailator.geometry.Positions
import org.springframework.stereotype.Service
import org.springframework.util.ResourceUtils
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

@Service
class WatermarkServiceImpl(val botConfig: BotConfig)
    : WatermarkService {

    override fun addWatermark(originalImg: BufferedImage): BufferedImage {
        var imgWithWatermark = addWatermark(originalImg, defineTextWatermark(originalImg.height))

        if (imgWithWatermark.height > botConfig.watermark.imgWmMinHeight) {
            val watermarkImage = ImageIO.read(ResourceUtils.getFile(botConfig.watermark.imgSrc))
            val imgWatermark = Watermark(Positions.TOP_LEFT, watermarkImage, botConfig.watermark.imgOpacity)
            imgWithWatermark = addWatermark(imgWithWatermark, imgWatermark)
        }

        return imgWithWatermark
    }

    private fun addWatermark(originalImage: BufferedImage, watermark: Watermark): BufferedImage {
        return Thumbnails.of(originalImage)
            .size(originalImage.width, originalImage.height)
            .watermark(watermark)
            .asBufferedImage()
    }

    private fun defineTextWatermark(originalImageHeight: Int): Watermark {
        val verticalTextWatermark = ImageIO.read(ResourceUtils.getFile(botConfig.watermark.verticalTextSrc))
        val horizontalTextWatermark = ImageIO.read(ResourceUtils.getFile(botConfig.watermark.horizontalTextSrc))

        return if (originalImageHeight > verticalTextWatermark.height * 2) {
            Watermark(Positions.CENTER_RIGHT, verticalTextWatermark, botConfig.watermark.textOpacity)
        } else {
            Watermark(Positions.BOTTOM_RIGHT, horizontalTextWatermark, botConfig.watermark.textOpacity)
        }
    }
}