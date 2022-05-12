package com.hynekbraun.randomanimalscompose1.presentation.animal_detail

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hynekbraun.randomanimalscompose1.domain.repository.AnimalFactRepository
import com.hynekbraun.randomanimalscompose1.presentation.ErrorState.ErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalFactDetailViewModel
@Inject
constructor(
    private val repository: AnimalFactRepository
) : ViewModel() {
    var state by mutableStateOf(AnimalFactDetailState())

    fun getAnimalFact(id: Int) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                val animalFact = repository.getAnimaFactById(id)
                animalFact.let { result ->
                    state = state.copy(data = result)
                }
            } catch (e: Exception) {
                //change later
                state = state.copy(error = ErrorState.NetworkError)
            }
        }
    }
}