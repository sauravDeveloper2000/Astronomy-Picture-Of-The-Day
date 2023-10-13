package com.example.astronomypictureoftheday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.astronomypictureoftheday.ui.home_screen.ApodViewModel
import com.example.astronomypictureoftheday.ui.theme.AstronomyPictureOfTheDayTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ApodViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AstronomyPictureOfTheDayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val retryAction: () -> Unit = viewModel::getApodData
                    ApodApp(
                        apodUiState = viewModel.apod,
                        retryAction
                    )
                }
            }
        }
    }
}
