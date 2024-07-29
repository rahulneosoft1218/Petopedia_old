package com.petopedia.app.domain.usecase

import com.petopedia.app.domain.model.PetDetails
import com.petopedia.app.domain.repository.PetsRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class PetInfoUseCase
    @Inject
    constructor(
        private val petsRepository: PetsRepository,
    ) : UseCase<PetInfoUseCase.Params, Any>(Dispatchers.IO) {
        override suspend fun execute(params: Params): PetDetails = petsRepository.getData()

        override suspend fun search(
            index: Int,
            input: String,
        ): PetDetails = petsRepository.filterList(index, input)

        override suspend fun getStatisticCount(breeds: List<PetDetails.Species.Breeds>): List<Map.Entry<Char, Int>> =
            petsRepository.getStatisticCount(breeds)

        data class Params(
            val name: String,
        )
    }
