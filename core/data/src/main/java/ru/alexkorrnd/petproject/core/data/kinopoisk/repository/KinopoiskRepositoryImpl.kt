package ru.alexkorrnd.petproject.core.data.kinopoisk.repository

import ru.alexkorrnd.core.network.kinopoisk.retrofit.KinopoiskService
import ru.alexkorrnd.petproject.core.data.kinopoisk.model.Movie
import ru.alexkorrnd.petproject.core.data.kinopoisk.model.asMovie
import javax.inject.Inject

internal class KinopoiskRepositoryImpl @Inject constructor(
    private val kinopoiskServiceLazy: dagger.Lazy<KinopoiskService>
) : KinopoiskRepository {

    private val kinopoiskService: KinopoiskService by lazy {
        kinopoiskServiceLazy.get()
    }

    override suspend fun loadMovies(type: String, page: Int): List<Movie> {
        return kinopoiskService.loadMovies(type, page)
            .movies.map { it.asMovie() }
    }
}
