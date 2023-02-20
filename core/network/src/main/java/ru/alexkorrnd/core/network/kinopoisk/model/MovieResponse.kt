package ru.alexkorrnd.core.network.kinopoisk.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class TopMoviesResponse(
    @SerialName("pagesCount") val pagesCount: Int,
    @SerialName("films") val movies: List<MovieResponse>,
)

@Serializable
class MovieResponse(
    @SerialName("filmId") val id: Int,
    @SerialName("nameRu") val name: String?,
    @SerialName("year") val year: String?,
    @SerialName("posterUrlPreview") val previewUrl: String?
)