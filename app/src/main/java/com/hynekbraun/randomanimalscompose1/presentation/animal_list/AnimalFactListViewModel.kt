package com.hynekbraun.randomanimalscompose1.presentation.animal_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hynekbraun.randomanimalscompose1.domain.repository.AnimalFactRepository
import com.hynekbraun.randomanimalscompose1.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalFactListViewModel
@Inject
constructor(
    private val repository: AnimalFactRepository
) : ViewModel() {
    var state by mutableStateOf(AnimalFactListState())

    init {
        getAnimalFactList()
    }

    fun onEvent(event: AnimalFactListEvent) {
        when (event) {
            is AnimalFactListEvent.Refresh -> {
                getAnimalFactList(true)
            }
            is AnimalFactListEvent.OnSearchQueryChange -> {
                //create search functionality
            }
        }
    }

    fun getAnimalFactList(fetchFromRemote: Boolean = false) {

        viewModelScope.launch {
            repository.getAnimalFacts(fetchFromRemote = fetchFromRemote)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { animalFacts ->
                                state =
                                    state.copy(data = animalFacts)
                            }
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                        is Resource.Error -> {
                            state = state.copy(error = result.message)
                        }
                    }
                }
        }

    }

}