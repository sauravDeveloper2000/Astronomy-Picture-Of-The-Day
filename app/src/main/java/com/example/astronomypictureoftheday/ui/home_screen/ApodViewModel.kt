package com.example.astronomypictureoftheday.ui.home_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.BuildDrawCacheParams
import androidx.core.os.BuildCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astronomypictureoftheday.BuildConfig
import com.example.astronomypictureoftheday.BuildConfig.API_KEY
import com.example.astronomypictureoftheday.ui.home_screen.repository.ApodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.internal.InjectedFieldSignature
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class ApodViewModel @Inject constructor(
    private val repositoryImpl: ApodRepository
) : ViewModel() {

    val apiKey = BuildConfig.API_KEY
    // Now apod(Astronomy Picture Of the Day) is mutable state object.
    // Which stores the most recent web request response.
    var apod: ApodUiState by mutableStateOf(ApodUiState.Loading)
        private set

    init {
        getApodData()
    }

    fun getApodData() {
        viewModelScope.launch {
            apod = ApodUiState.Loading
            apod = try {
                val responseHolder = repositoryImpl.getApodData(API_KEY)
                ApodUiState.SuccessOne(dataHolder = responseHolder)
            } catch (e: IOException) {
                ApodUiState.ErrorOne(exception = e.toString())
            } catch (e: HttpException) {
                ApodUiState.ErrorOne(exception = e.toString())
            }
        }
    }
}