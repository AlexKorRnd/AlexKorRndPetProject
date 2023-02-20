package ru.alexkorrnd.petproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import ru.alexkorrnd.designsystem.core.theme.AlexKorRndPetProjectTheme
import ru.alexkorrnd.petproject.feature.kinopoisk.presentation.KinopoiskTopFilmsRoute
import ru.alexkorrnd.petproject.feature.kinopoisk.presentation.KinopoiskTopFilmsViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val kinopoiskTopFilmsViewModel by viewModels<KinopoiskTopFilmsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlexKorRndPetProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    KinopoiskTopFilmsRoute(topFilmsViewModel = kinopoiskTopFilmsViewModel)
                }
            }
        }
    }
}
