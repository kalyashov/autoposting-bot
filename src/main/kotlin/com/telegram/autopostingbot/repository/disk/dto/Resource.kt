package com.telegram.autopostingbot.repository.disk.dto

import com.google.gson.annotations.SerializedName

import com.yandex.disk.rest.util.ISO8601

import java.util.*

data class Resource(
    @SerializedName("public_key")
    val publicKey: String? = null,

    @SerializedName("_embedded")
    val resourceList: ResourceList,

    @SerializedName("resource_id")
    val id: String,

    @SerializedName("file")
    val file: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("created")
    val created: String,

    @SerializedName("public_url")
    val publicUrl: String? = null,

    @SerializedName("origin_path")
    val originPath: String? = null,

    @SerializedName("modified")
    val modified: String? = null,

    @SerializedName("path")
    val path: String? = null,

    @SerializedName("md5")
    val md5: String? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("mime_type")
    val mimeType: String? = null,

    @SerializedName("media_type")
    val mediaType: String,

    @SerializedName("preview")
    val preview: String? = null,

    @SerializedName("size")
    val size: Int = 0,

    @SerializedName("custom_properties")
    val properties: Any? = null
) {
    fun getCreated(): Date {
        return ISO8601.parse(created)
    }
}