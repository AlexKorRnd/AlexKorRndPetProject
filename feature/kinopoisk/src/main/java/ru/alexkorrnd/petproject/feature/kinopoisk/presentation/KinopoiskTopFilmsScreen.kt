package ru.alexkorrnd.petproject.feature.kinopoisk.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
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
    when(state) {
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

@Composable
fun SuccessState(
    state: TopFilmsUiState.Success,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier)  {
        items(
            items = state.films.toTypedArray(),
            key = { it.id }
        ) {movie ->
            MovieItem(
                movie = movie,
                modifier = Modifier.padding(24.dp)
            )
        }
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    ) {
        Column {
            Image(
                painter = painterResource(ru.alexkorrnd.petproject.core.designsystem.R.drawable.test_image),
                contentDescription = null
            )
            Row {
                Text(text = movie.name)
                Text(text = movie.year)
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

@Preview(name = "phone", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480")
@Composable
fun KinopoiskTopFilmsScreenSuccessPreview() {
    AlexKorRndPetProjectTheme {
        KinopoiskTopFilmsScreen(
            state = TopFilmsUiState.Success(previewsMovies)
        )
    }
}
