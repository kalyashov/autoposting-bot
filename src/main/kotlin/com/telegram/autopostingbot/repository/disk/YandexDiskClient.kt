package com.telegram.autopostingbot.repository.disk

import com.telegram.autopostingbot.config.BotConfig
import com.telegram.autopostingbot.entity.ContentInfo
import com.yandex.disk.rest.*
import com.yandex.disk.rest.json.Resource
import com.yandex.disk.rest.retrofit.CloudApi
import com.yandex.disk.rest.retrofit.ErrorHandlerImpl
import com.yandex.disk.rest.retrofit.RequestInterceptorImpl

import org.springframework.stereotype.Service
import retrofit.RestAdapter
import retrofit.client.OkClient
import java.awt.image.BufferedImage
import java.net.URL
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneId
import javax.imageio.ImageIO

@Service
class YandexDiskClient(private val botConfig: BotConfig) {
    private var cloudApi: CloudApi? = null

    init {
        val credentials = Credentials(botConfig.disk.userAgent, botConfig.disk.token)

        this.cloudApi = RestAdapter.Builder()
            .setClient(OkClient(OkHttpClientFactory.makeClient()))
            .setEndpoint(botConfig.disk.endpoint)
            .setRequestInterceptor(RequestInterceptorImpl(credentials.headers))
            .setErrorHandler(ErrorHandlerImpl())
            .build()
            .create(CloudApi::class.java)
    }

    companion object {
        const val IMG_MEDIA_TYPE = "image"
        const val CREATED_FIELD_NAME = "created"
        const val PAGE_SIZE = 100
    }

    fun getImageContentByDate(startDateLimit: LocalDateTime): List<ContentInfo> {
        var needLoad = true
        var pageNumber = 0
        val imgResources = mutableListOf<Resource>()

        while (needLoad) {
            var args = buildRqArgs(PAGE_SIZE, pageNumber)
            val resourcesList = cloudApi!!.getResources(args.path, args.fields, args.limit, args.offset, args.sort,
                args.previewSize, args.previewCrop).resourceList

            for (res in resourcesList.items) {
                if (res.mediaType == IMG_MEDIA_TYPE) {
                    if (res.created >= Timestamp.valueOf(startDateLimit)) {
                        imgResources.add(res)
                    } else {
                        needLoad = false
                    }
                }
            }

            if (PAGE_SIZE * pageNumber < resourcesList.total) {
                pageNumber++
            } else {
                needLoad = false
            }
        }

        return imgResources.map {
            ContentInfo(it.name, it.path.path, LocalDateTime.ofInstant(it.created.toInstant(), ZoneId.systemDefault())) }
    }

    fun getImageByPath(path: String): BufferedImage {
        val imgLink = cloudApi!!.getDownloadLink(path).href
        return ImageIO.read(URL(imgLink))
    }

    private fun buildRqArgs(pageSize: Int, pageNumber: Int): ResourcesArgs {
        return ResourcesArgs.Builder()
            .setPath(botConfig.disk.folder)
            .setSort(CREATED_FIELD_NAME)
            .setLimit(pageSize)
            .setOffset(pageSize * pageNumber)
            .build()
    }
}