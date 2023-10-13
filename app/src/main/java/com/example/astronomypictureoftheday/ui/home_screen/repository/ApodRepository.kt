package com.example.astronomypictureoftheday.ui.home_screen.repository

import com.example.astronomypictureoftheday.model_class.Apod

interface ApodRepository {
    suspend fun getApodData(apiKey: String): Apod
}