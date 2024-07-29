package com.example.carousel.di

import com.example.carousel.data.PetsDataSource
import com.example.carousel.data.PetsDataSourceImpl
import com.example.carousel.domain.repository.PetsRepository
import com.example.carousel.domain.repository.PetsRepositoryImpl
import com.example.carousel.domain.usecase.CarouselUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideGetUserDetailsUseCase(): CarouselUseCase = CarouselUseCase(providePetsRepository())

    @Provides
    fun providePetsDataSource(): PetsDataSource = PetsDataSourceImpl()

    @Provides
    fun providePetsRepository(): PetsRepository = PetsRepositoryImpl(providePetsDataSource())
}
