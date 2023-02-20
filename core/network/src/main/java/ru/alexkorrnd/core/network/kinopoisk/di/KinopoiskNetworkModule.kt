package ru.alexkorrnd.core.network.kinopoisk.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.alexkorrnd.core.network.kinopoisk.retrofit.KinopoiskRetrofitNetwork
import ru.alexkorrnd.core.network.kinopoisk.retrofit.KinopoiskService

private const val AUTH_HEADER_NAME = "X-API-KEY"
private const val AUTH_HEADER_VALUE = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"
private const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/"
private val contentType = "application/json".toMediaType()

@Module
@InstallIn(SingletonComponent::class)
internal interface KinopoiskNetworkModule {

    @Binds
    fun bindKinopoiskService(kinopoiskRetrofitNetwork: KinopoiskRetrofitNetwork): KinopoiskService

    companion object {

        @Provides
        fun provideOkHttp(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor( // TODO: Decide logging logic
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                )
                .addNetworkInterceptor { chain ->
                    chain.request()
                        .newBuilder()
                        .addHeader(AUTH_HEADER_NAME, AUTH_HEADER_VALUE)
                        .build()
                        .let { chain.proceed(it) }
                }
                .build()
        }

        @Provides
        fun provideJson(): Json {
            return Json { ignoreUnknownKeys = true }
        }

        @OptIn(ExperimentalSerializationApi::class)
        @Provides
        fun provideRetrofit(
            okHttpClient: OkHttpClient,
            json: Json
        ): Retrofit {
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(json.asConverterFactory(contentType))
                .build()
        }
    }
}