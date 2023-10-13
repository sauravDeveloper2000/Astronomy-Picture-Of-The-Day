package com.example.astronomypictureoftheday.ui.home_screen.repository

import com.example.astronomypictureoftheday.data.ApodApi
import com.example.astronomypictureoftheday.model_class.Apod

class ApodRepositoryImpl(
    private val apodApi: ApodApi
): ApodRepository {
    override suspend fun getApodData(
        apiKey: String
    ): Apod {
        return apodApi.getApod(apiKey)
    }
}