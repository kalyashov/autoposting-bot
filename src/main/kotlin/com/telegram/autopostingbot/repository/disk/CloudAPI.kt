package com.telegram.autopostingbot.repository.disk

import com.telegram.autopostingbot.repository.disk.dto.Resource
import com.yandex.disk.rest.exceptions.NetworkIOException
import com.yandex.disk.rest.exceptions.ServerIOException

import retrofit.http.GET
import retrofit.http.Query

interface CloudAPI {

    @GET("/v1/disk/resources")
    @Throws(NetworkIOException::class, ServerIOException::class)
    fun getResources(
        @Query("path") path: String?, @Query("fields") fields: String?,
        @Query("limit") limit: Int?, @Query("offset") offset: Int?,
        @Query("sort") sort: String?, @Query("preview_size") previewSize: String?,
        @Query("preview_crop") previewCrop: Boolean?
    ): Resource?
}