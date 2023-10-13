package com.example.astronomypictureoftheday

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.example.astronomypictureoftheday.ui.home_screen.ApodUiState
import com.example.astronomypictureoftheday.ui.home_screen.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApodApp(
    apodUiState: ApodUiState,
    retryAction: () -> Unit
) {
    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = {
            ApodTopBar(scrollBehaviour = scrollBehaviour)
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen(
                apodUiState = apodUiState,
                contentPadding = it,
                retryAction = retryAction
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApodTopBar(
    scrollBehaviour: TopAppBarScrollBehavior
) {
    TopAppBar(
        scrollBehavior = scrollBehaviour,
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color(0xffbdbebd)
        )
    )
}