package com.hynekbraun.randomanimalscompose1.presentation.animal_list

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hynekbraun.randomanimalscompose1.domain.repository.AnimalFactRepository
import com.hynekbraun.randomanimalscompose1.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
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
                state = state.copy(isRefreshing = true)
                getAnimalFactList(true)
            }
            is AnimalFactListEvent.OnSearchQueryChange -> {
                //create search functionality
            }
        }
    }

    private fun getAnimalFactList(fetchFromRemote: Boolean = false) {

        viewModelScope.launch {
            repository.getAnimalFacts(fetchFromRemote = fetchFromRemote)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { animalFacts ->
                                state =
                                    state.copy(data = animalFacts)
                            }
                            //FIXME This should not be hard coded, but I have no idea where to put it to work
                            delay(1500L)
                            state = state.copy(isRefreshing = false)

                            Log.d("TAG", "AnimalFactViewModel - Success")
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                            Log.d("TAG", "AnimalFactViewModel - Loading")

                        }
                        is Resource.Error -> {
                            state = state.copy(error = result.message)
                            Log.d("TAG", "AnimalFactViewModel - Error")

                        }
                    }
                }
        }

    }

}