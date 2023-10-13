package com.example.astronomypictureoftheday.dependency_injection

import com.example.astronomypictureoftheday.data.ApodApi
import com.example.astronomypictureoftheday.ui.home_screen.repository.ApodRepository
import com.example.astronomypictureoftheday.ui.home_screen.repository.ApodRepositoryImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

//https://api.nasa.gov/planetary/apod?api_key=c6821RAviMgVrtNabIkBdNO0JUwnX5H8PLxTqmBC
private const val BASE_URL = "https://api.nasa.gov/planetary/"

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun providesRetrofitInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun providesApodApi(retrofit: Retrofit): ApodApi{
        return retrofit.create(ApodApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRepositoryInstance(
        apod: ApodApi
    ): ApodRepository{
        return ApodRepositoryImpl(apod)
    }
}