package com.example.astronomypictureoftheday.data

import com.example.astronomypictureoftheday.model_class.Apod
import retrofit2.http.GET
import retrofit2.http.Query


interface ApodApi {

    @GET("apod")
    suspend fun getApod(@Query("api_key") apiKey: String): Apod
}