package ru.alexkorrnd.petproject.feature.kinopoisk.presentation

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4_XL
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.alexkorrnd.designsystem.core.theme.AlexKorRndPetProjectTheme
import ru.alexkorrnd.petproject.core.data.kinopoisk.model.Movie
import ru.alexkorrnd.petproject.core.data.kinopoisk.model.previewsMovies

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun KinopoiskTopFilmsRoute(
    modifier: Modifier = Modifier,
    topFilmsViewModel: KinopoiskTopFilmsViewModel
) {
    val state: TopFilmsUiState by topFilmsViewModel.state.collectAsStateWithLifecycle()
    KinopoiskTopFilmsScreen(
        state = state,
        modifier = modifier
    )
}

@Composable
fun KinopoiskTopFilmsScreen(
    state: TopFilmsUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        when (state) {
            is TopFilmsUiState.Loading -> {
                Text(text = "Загружаем данные...")
            }
            is TopFilmsUiState.Success -> {
                SuccessState(state, modifier)
            }
            is TopFilmsUiState.Error -> {
                ErrorState(state, modifier)
            }
        }
    }
}



@Composable
fun SuccessState(
    state: TopFilmsUiState.Success,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = modifier
    ) {
        items(
            items = state.films.toTypedArray(),
            key = { it.id }
        ) { movie ->
            MovieItem(
                movie = movie,
            )
        }
    }
}

private val CARD_CONTENT_MIN_HEIGHT = 63.dp

@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 93.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 16.dp, top = 15.dp, end = 16.dp, bottom = 15.dp),
        ) {
            Image(
                painter = painterResource(ru.alexkorrnd.petproject.core.designsystem.R.drawable.test_image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(width = 40.dp, height = CARD_CONTENT_MIN_HEIGHT)
                    .clip(RoundedCornerShape(5.dp))
            )
            Spacer(modifier = Modifier.size(15.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .heightIn(min = CARD_CONTENT_MIN_HEIGHT),
            ) {
                Text(
                    text = movie.name,
                    style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Medium),
                )
                Text(
                    text = movie.year,
                    style = MaterialTheme.typography.body2,
                )
            }
        }
    }
}

@Composable
fun ErrorState(
    state: TopFilmsUiState.Error,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Произошла ошибка. Повторите запрос позднее",
        modifier = modifier
    )
}

@RequiresApi(Build.VERSION_CODES.FROYO)
@Preview(name = "night", showBackground = true, device = PIXEL_4_XL, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun KinopoiskTopFilmsScreenSuccessPreviewNight() {
    AlexKorRndPetProjectTheme {
        KinopoiskTopFilmsScreen(
            state = TopFilmsUiState.Success(previewsMovies)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.FROYO)
@Preview(
    name = "day",
    showBackground = true,
    device = PIXEL_4_XL,
    backgroundColor = 0xFFFFFF,
    uiMode = UI_MODE_NIGHT_NO
)
@Composable
fun KinopoiskTopFilmsScreenSuccessPreviewDay() {
    AlexKorRndPetProjectTheme {
        KinopoiskTopFilmsScreen(
            state = TopFilmsUiState.Success(previewsMovies)
        )
    }
}
