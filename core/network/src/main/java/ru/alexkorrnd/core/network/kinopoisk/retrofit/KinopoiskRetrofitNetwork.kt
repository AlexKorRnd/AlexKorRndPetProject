package ru.alexkorrnd.core.network.kinopoisk.retrofit

import retrofit2.Retrofit
import ru.alexkorrnd.core.network.kinopoisk.model.TopMoviesResponse
import javax.inject.Inject

internal class KinopoiskRetrofitNetwork @Inject constructor(
    private val retrofitLazy: dagger.Lazy<Retrofit>
) : KinopoiskService {

    private val retrofitApi: KinopoiskService by lazy {
        retrofitLazy.get().create(KinopoiskService::class.java)
    }

    override suspend fun loadMovies(type: String, page: Int): TopMoviesResponse {
        return retrofitApi.loadMovies(type, page)
    }
}