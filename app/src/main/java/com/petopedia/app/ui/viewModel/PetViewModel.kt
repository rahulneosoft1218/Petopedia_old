package com.petopedia.app.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petopedia.app.domain.model.PetDetails
import com.petopedia.app.domain.usecase.PetInfoUseCase
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
class PetViewModel
    @Inject
    constructor(
        private val petInfoUseCase: PetInfoUseCase,
    ) : ViewModel() {
        private val petDataHolder = MutableStateFlow(PetDetails(emptyList()))
        val petData: StateFlow<PetDetails> = petDataHolder.asStateFlow()

        private val charCount = MutableSharedFlow<List<Map.Entry<Char, Int>>>()
        val topCharCount: SharedFlow<List<Map.Entry<Char, Int>>> = charCount.asSharedFlow()

        fun getPetList(breed: String) =
            viewModelScope.launch(Dispatchers.IO) {
                petDataHolder.emit(petInfoUseCase.execute(PetInfoUseCase.Params(breed)))
            }

        fun getFilteredData(
            index: Int,
            input: String,
        ) {
            viewModelScope.launch(Dispatchers.IO) {
                petDataHolder.emit(petInfoUseCase.search(index, input))
            }
        }

        fun getStatisticCount(breedsList: List<PetDetails.Species.Breeds>) {
            viewModelScope.launch(Dispatchers.IO) {
                charCount.emit(petInfoUseCase.getStatisticCount(breedsList))
            }
        }
    }
