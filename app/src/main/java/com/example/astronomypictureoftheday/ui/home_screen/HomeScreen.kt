package com.example.astronomypictureoftheday.ui.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
    modifier: Modifier = Modifier,
    apodUiState: ApodUiState,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when (apodUiState) {

        ApodUiState.Loading -> {
            LoadingScreen()
        }

        is ApodUiState.SuccessOne -> {
            ScrollableLoadingScreen(
                apod = apodUiState.dataHolder,
                modifier = modifier
                    .padding(
                        start = 11.dp,
                        end = 11.dp,
                        top = 11.dp,
                        bottom = 11.dp
                    ),
                contentPadding = contentPadding,
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
    Image(
        painter = painterResource(id = R.drawable.loading),
        contentDescription = " "
    )
}

@Composable
fun ScrollableLoadingScreen(
    apod: Apod,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    retryAction: () -> Unit
){
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ){
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
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.apod_title, apod.title, apod.date),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(9.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(apod.hdUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                error = painterResource(id = R.drawable.ic_broken_image_24),
                placeholder = painterResource(id = R.drawable.loading)
            )
            Text(
                text = apod.explanation,
                fontSize = 14.sp,
                modifier = Modifier.padding(5.dp)
            )
            if (apod.copyright != null){
                Text(
                    text = "Author - ${apod.copyright}",
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
    }
    Button(
        onClick = retryAction
    ) {
        Text(text = stringResource(id = R.string.retry))
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
