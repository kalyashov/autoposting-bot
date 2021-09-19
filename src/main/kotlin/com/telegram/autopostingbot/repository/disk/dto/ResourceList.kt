package com.telegram.autopostingbot.repository.disk.dto

import com.google.gson.annotations.SerializedName

data class ResourceList(
    @SerializedName("sort")
    val sort: String? = null,

    @SerializedName("public_key")
    val publicKey: String? = null,

    @SerializedName("items")
    val items: List<Resource>,

    @SerializedName("path")
    val path: String,

    @SerializedName("limit")
    val limit: Int,

    @SerializedName("offset")
    val offset: Int,

    @SerializedName("total")
    val total: Int
)