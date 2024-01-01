package com.example.astronomypictureoftheday.ui.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.astronomypictureoftheday.R
import com.example.astronomypictureoftheday.model_class.Apod

@Composable
fun HomeScreen(
    retryAction: () -> Unit,
    modifier: Modifier,
    apodUiState: ApodUiState,
) {
    when (apodUiState) {
        ApodUiState.Loading -> {
            LoadingScreen()
        }
        is ApodUiState.SuccessOne -> {
            ScrollableLoadingScreen(
                apod = apodUiState.dataHolder,
                modifier = modifier,
                retryAction = retryAction
            )
        }
        is ApodUiState.ErrorOne -> {
            ErrorScreen(
                retryAction
            )
        }
    }
}

// Composable to show loading screen.
@Composable
fun LoadingScreen() {
//    Image(
//        painter = painterResource(id = R.drawable.loading),
//        contentDescription = " "
//    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ScrollableLoadingScreen(
    apod: Apod,
    modifier: Modifier,
    retryAction: () -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(15.dp)
    ) {
        item {
            ApodScreen(
                apod = apod,
                modifier = Modifier.fillMaxSize(),
                retryAction = retryAction
            )
        }
    }
}

// Composable for success screen.
@Composable
fun ApodScreen(
    apod: Apod,
    modifier: Modifier = Modifier,
    retryAction: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = modifier
        ) {
            Text(
                text = stringResource(R.string.apod_title, apod.title, apod.date),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(9.dp),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            if (apod.url != null) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(MaterialTheme.shapes.small),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(apod.url)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    error = painterResource(id = R.drawable.ic_broken_image_24),
                    placeholder = painterResource(id = R.drawable.loading)
                )
            } else {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(MaterialTheme.shapes.small),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(apod.url)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    error = painterResource(id = R.drawable.ic_broken_image_24),
                    placeholder = painterResource(id = R.drawable.loading)
                )
            }
            Text(
                text = apod.explanation,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(5.dp)
            )
            if (apod.copyright != null) {
                Text(
                    text = "Author - ${apod.copyright}",
                    modifier = Modifier.padding(5.dp)
                )
            }

        }
        Button(
            onClick = retryAction,
            shape = MaterialTheme.shapes.small
        ) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

// Composable to show error on the screen.
@Composable
fun ErrorScreen(
    retryAction: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_cloud_off_24),
            contentDescription = " ",
            modifier = Modifier.fillMaxSize(0.3f)
        )
        Text(
            text = stringResource(id = R.string.loading_failed),
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = retryAction
        ) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}
