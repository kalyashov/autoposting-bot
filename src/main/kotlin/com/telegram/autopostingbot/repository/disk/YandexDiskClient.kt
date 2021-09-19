package com.telegram.autopostingbot.repository.disk

import com.telegram.autopostingbot.config.BotConfig
import com.telegram.autopostingbot.entity.Content
import com.yandex.disk.rest.Credentials
import com.yandex.disk.rest.OkHttpClientFactory
import com.yandex.disk.rest.ResourcesArgs
import com.yandex.disk.rest.retrofit.ErrorHandlerImpl
import com.yandex.disk.rest.retrofit.RequestInterceptorImpl
import org.springframework.stereotype.Service
import retrofit.RestAdapter
import retrofit.client.OkClient

@Service
class YandexDiskClient(private val botConfig: BotConfig) {
    private var cloudAPI: CloudAPI? = null

    constructor() {
        val credentials = Credentials(botConfig.disk.username, botConfig.disk.token)

        this.cloudAPI = RestAdapter.Builder()
            .setClient(OkClient(OkHttpClientFactory.makeClient()))
            .setEndpoint(botConfig.disk.endpoint)
            .setRequestInterceptor(RequestInterceptorImpl(credentials.headers))
            .setErrorHandler(ErrorHandlerImpl())
            .build()
            .create(CloudAPI::class.java)
    }

    companion object {
        const val IMG_MEDIA_TYPE = "image"
    }

    fun getContentList(): List<Content> {
        val args = ResourcesArgs.Builder()
            .setPath(botConfig.disk.folder)
            .build()

        val resource = cloudAPI!!.getResources(args.path, args.fields, args.limit, args.offset,
            args.sort, args.previewSize, args.previewCrop)

        return resource!!.resourceList.items
            .filter { it.mediaType == IMG_MEDIA_TYPE }
            .map { Content(it.id)
        }
    }
}