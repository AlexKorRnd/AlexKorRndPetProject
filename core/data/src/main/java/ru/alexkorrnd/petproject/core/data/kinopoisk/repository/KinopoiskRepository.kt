package ru.alexkorrnd.petproject.core.data.kinopoisk.repository

import ru.alexkorrnd.petproject.core.data.kinopoisk.model.Movie

interface KinopoiskRepository {

    suspend fun loadMovies(type: String, page: Int): List<Movie>
}