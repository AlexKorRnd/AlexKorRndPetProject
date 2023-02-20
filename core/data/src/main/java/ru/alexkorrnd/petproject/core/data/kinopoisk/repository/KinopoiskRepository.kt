package ru.alexkorrnd.petproject.core.data.kinopoisk.repository

import ru.alexkorrnd.petproject.core.data.kinopoisk.model.Movie

interface KinopoiskRepository {

    suspend fun loadTopMovies(page: Int): List<Movie>
}