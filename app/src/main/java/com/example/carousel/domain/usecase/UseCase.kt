package com.example.carousel.domain.usecase

import com.example.carousel.domain.model.PetDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<in Params, out Type>(
    private val coroutineDispatcher: CoroutineDispatcher,
) {
    abstract suspend fun execute(params: Params): Type

    abstract suspend fun search(
        index: Int,
        input: String,
    ): Type

    abstract suspend fun getStatisticCount(breeds: List<PetDetails.Species.Breeds>): Type

    suspend operator fun invoke(params: Params): Type =
        withContext(coroutineDispatcher) {
            execute(params)
        }
}
