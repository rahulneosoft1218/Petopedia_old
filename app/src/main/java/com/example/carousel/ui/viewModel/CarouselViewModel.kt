package com.example.carousel.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carousel.domain.model.PetDetails
import com.example.carousel.domain.usecase.CarouselUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarouselViewModel
    @Inject
    constructor(
        private val carouselUseCase: CarouselUseCase,
    ) : ViewModel() {
        private val carouselHolder = MutableStateFlow(PetDetails(emptyList()))
        val carouselData: StateFlow<PetDetails> = carouselHolder.asStateFlow()

        private val charCount = MutableSharedFlow<List<Map.Entry<Char, Int>>>()
        val topCharCount: SharedFlow<List<Map.Entry<Char, Int>>> = charCount.asSharedFlow()

        fun getPetList(breed: String) =
            viewModelScope.launch(Dispatchers.IO) {
                carouselHolder.emit(carouselUseCase.execute(CarouselUseCase.Params(breed)))
            }

        fun getFilteredData(
            index: Int,
            input: String,
        ) {
            viewModelScope.launch(Dispatchers.IO) {
                carouselHolder.emit(carouselUseCase.search(index, input))
            }
        }

        fun getStatisticCount(breedsList: List<PetDetails.Species.Breeds>) {
            viewModelScope.launch(Dispatchers.IO) {
                charCount.emit(carouselUseCase.getStatisticCount(breedsList))
            }
        }
    }
