package ru.alexkorrnd.petproject.core.data.kinopoisk.model

import ru.alexkorrnd.core.network.kinopoisk.model.MovieResponse

data class Movie(
    val id: Int,
    val name: String?,
    val year: String?,
    val previewUrl: String?
)

fun MovieResponse.asMovie(): Movie = Movie(
    id = id,
    name = name,
    year = year,
    previewUrl = previewUrl
)