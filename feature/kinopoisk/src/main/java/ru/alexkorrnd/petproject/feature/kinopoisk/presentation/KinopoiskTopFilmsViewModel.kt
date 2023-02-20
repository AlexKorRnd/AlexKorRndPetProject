package ru.alexkorrnd.petproject.feature.kinopoisk.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import ru.alexkorrnd.petproject.core.data.kinopoisk.model.Movie
import ru.alexkorrnd.petproject.core.data.kinopoisk.repository.KinopoiskRepository
import javax.inject.Inject

@HiltViewModel
class KinopoiskTopFilmsViewModel @Inject constructor(
    private val kinopoiskRepository: KinopoiskRepository
) : ViewModel() {

    private val _state: MutableStateFlow<TopFilmsUiState> = MutableStateFlow(TopFilmsUiState.Loading)
    val state: StateFlow<TopFilmsUiState>
        get() = _state


}

sealed interface TopFilmsUiState {
    data class Success(val films: List<Movie>) : TopFilmsUiState
    object Error : TopFilmsUiState
    object Loading : TopFilmsUiState
}