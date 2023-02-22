package ru.alexkorrnd.petproject.core.data.kinopoisk.model

import ru.alexkorrnd.core.network.kinopoisk.model.MovieResponse

data class Movie(
    val id: Int,
    val name: String,
    val year: String,
    val previewUrl: String?
)

val previewsMovies = listOf(
    Movie(
        id = 4370148,
        name = "Чебурашка",
        year = "2022",
        previewUrl = "https://kinopoiskapiunofficial.tech/images/posters/kp_small/4370148.jpg"
    ),
    Movie(
        id = 4370149,
        name = "Аватар: Путь воды",
        year = "2022",
        previewUrl = "https://kinopoiskapiunofficial.tech/images/posters/kp_small/505898.jpg"
    ),
    Movie(
        id = 4370150,
        name = "Кот в сапогах 2: Последнее желание",
        year = "2022",
        previewUrl = "https://kinopoiskapiunofficial.tech/images/posters/kp_small/840821.jpg"
    ),
)

fun MovieResponse.asMovie(): Movie = Movie(
    id = id,
    name = name ?: "",
    year = year ?: "",
    previewUrl = previewUrl
)