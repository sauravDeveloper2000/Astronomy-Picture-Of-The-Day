package com.example.astronomypictureoftheday.ui.home_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astronomypictureoftheday.ui.home_screen.repository.ApodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.internal.InjectedFieldSignature
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

//https://api.nasa.gov/planetary/apod?api_key=c6821RAviMgVrtNabIkBdNO0JUwnX5H8PLxTqmBC
private const val API_KEY = "c6821RAviMgVrtNabIkBdNO0JUwnX5H8PLxTqmBC"

@HiltViewModel
class ApodViewModel @Inject constructor(
    private val repositoryImpl: ApodRepository
) : ViewModel() {

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