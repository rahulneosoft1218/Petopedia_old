package com.petopedia.app.di

import com.petopedia.app.data.PetsDataSource
import com.petopedia.app.data.PetsDataSourceImpl
import com.petopedia.app.domain.repository.PetsRepository
import com.petopedia.app.domain.repository.PetsRepositoryImpl
import com.petopedia.app.domain.usecase.PetInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideGetUserDetailsUseCase(): PetInfoUseCase = PetInfoUseCase(providePetsRepository())

    @Provides
    fun providePetsDataSource(): PetsDataSource = PetsDataSourceImpl()

    @Provides
    fun providePetsRepository(): PetsRepository = PetsRepositoryImpl(providePetsDataSource())
}
