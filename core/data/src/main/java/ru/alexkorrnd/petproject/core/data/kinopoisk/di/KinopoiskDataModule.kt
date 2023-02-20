package ru.alexkorrnd.petproject.core.data.kinopoisk.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import ru.alexkorrnd.petproject.core.data.kinopoisk.repository.KinopoiskRepository
import ru.alexkorrnd.petproject.core.data.kinopoisk.repository.KinopoiskRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
internal interface KinopoiskDataModule {

    @Binds
    fun bindRepository(kinopoiskRepositoryImpl: KinopoiskRepositoryImpl): KinopoiskRepository
}