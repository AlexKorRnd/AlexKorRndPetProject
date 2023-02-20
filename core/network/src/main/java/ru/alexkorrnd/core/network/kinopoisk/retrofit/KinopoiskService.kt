package ru.alexkorrnd.core.network.kinopoisk.retrofit

import retrofit2.http.GET
import retrofit2.http.Query
import ru.alexkorrnd.core.network.kinopoisk.model.TopMoviesResponse

interface KinopoiskService {

    @GET("films/top")
    suspend fun loadMovies(@Query("type") type: String, @Query("page") page: Int): TopMoviesResponse

}