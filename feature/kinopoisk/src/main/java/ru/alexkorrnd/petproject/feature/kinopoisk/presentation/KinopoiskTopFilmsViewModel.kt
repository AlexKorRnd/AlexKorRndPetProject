package ru.alexkorrnd.petproject.feature.kinopoisk.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.alexkorrnd.core.utils.coroutines.runCatchingNonCancellation
import ru.alexkorrnd.petproject.core.data.kinopoisk.model.Movie
import ru.alexkorrnd.petproject.core.data.kinopoisk.repository.KinopoiskRepository
import javax.inject.Inject

@HiltViewModel
class KinopoiskTopFilmsViewModel @Inject constructor(
    private val kinopoiskRepository: KinopoiskRepository
) : ViewModel() {

    private val _state: MutableStateFlow<TopFilmsUiState> =
        MutableStateFlow(TopFilmsUiState.Loading)
    val state: StateFlow<TopFilmsUiState>
        get() = _state

    init {
        viewModelScope.launch {
            runCatchingNonCancellation {
                kinopoiskRepository.loadTopMovies(1)
            }.onSuccess {
                _state.emit(TopFilmsUiState.Success(it))
            }.onFailure {
                Log.e("test____", it.message, it)
                _state.emit(TopFilmsUiState.Error)
            }
        }
    }
}

sealed interface TopFilmsUiState {
    data class Success(val films: List<Movie>) : TopFilmsUiState
    object Error : TopFilmsUiState
    object Loading : TopFilmsUiState
}