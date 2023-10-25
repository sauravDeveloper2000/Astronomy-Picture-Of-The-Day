package com.example.astronomypictureoftheday.model_class

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Apod(
    val copyright: String? = null,
    val date: String,
    val explanation: String,
    @SerialName("hdurl")
    val hdUrl: String? = null,
    @SerialName("media_type")
    val mediaType: String? = null,
    @SerialName("service_version")
    val serviceVersion: String? = null,
    val title: String,
    val url: String? = null
)