package com.example.carousel.data

import com.example.carousel.domain.model.Animal
import com.example.carousel.domain.model.PetDetails

interface PetsDataSource {
    suspend fun getData(): PetDetails

    suspend fun getBreedsListFor(
        animal: Animal,
        icon: Int,
    ): List<PetDetails.Species.Breeds>

    suspend fun filterList(
        index: Int,
        input: String,
    ): PetDetails

    suspend fun getStatisticCount(breeds: List<PetDetails.Species.Breeds>): List<Map.Entry<Char, Int>>
}
