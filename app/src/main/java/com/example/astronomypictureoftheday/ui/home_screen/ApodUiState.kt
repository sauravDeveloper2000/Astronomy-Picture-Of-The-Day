package com.example.astronomypictureoftheday.ui.home_screen

import com.example.astronomypictureoftheday.model_class.Apod

// This UiState handles the web response.
sealed interface ApodUiState{
    object Loading: ApodUiState
    data class SuccessOne(val dataHolder: Apod): ApodUiState
    data class ErrorOne(val exception: String): ApodUiState
}