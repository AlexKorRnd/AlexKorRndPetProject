package ru.alexkorrnd.petproject.core.data.kinopoisk.repository

import ru.alexkorrnd.core.network.kinopoisk.retrofit.KinopoiskService
import ru.alexkorrnd.petproject.core.data.kinopoisk.model.Movie
import ru.alexkorrnd.petproject.core.data.kinopoisk.model.asMovie
import javax.inject.Inject

private const val TOP_FILMS_TYPE = "TOP_100_POPULAR_FILMS"

internal class KinopoiskRepositoryImpl @Inject constructor(
    private val kinopoiskServiceLazy: dagger.Lazy<KinopoiskService>
) : KinopoiskRepository {

    private val kinopoiskService: KinopoiskService by lazy {
        kinopoiskServiceLazy.get()
    }

    override suspend fun loadTopMovies(page: Int): List<Movie> {
        return kinopoiskService.loadMovies(TOP_FILMS_TYPE, page)
            .movies.map { it.asMovie() }
    }
}
