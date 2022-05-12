package com.hynekbraun.randomanimalscompose1.presentation.animal_list

import android.app.Application
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.hynekbraun.randomanimalscompose1.domain.repository.AnimalFactRepository
import com.hynekbraun.randomanimalscompose1.presentation.ErrorState.ErrorState
import com.hynekbraun.randomanimalscompose1.presentation.ErrorState.network_check.currentConnectivityState
import com.hynekbraun.randomanimalscompose1.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalFactListViewModel
@Inject
constructor(
    private val repository: AnimalFactRepository,
    application: Application
) : AndroidViewModel(application) {
    var state by mutableStateOf(AnimalFactListState())

    init {
        getAnimalFactList()
        Log.d("TAG", "View model tag")
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
            if (getApplication<Application>().applicationContext.currentConnectivityState) {
                repository.getAnimalFacts(fetchFromRemote = fetchFromRemote)
                    .collect { result ->
                        when (result) {
                            is Resource.Success -> {
                                result.data?.let { animalFacts ->
                                    state =
                                        state.copy(data = animalFacts, error = ErrorState.NoError)
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
                                state = state.copy(error = result.error)
                                Log.d("TAG", "AnimalFactViewModel - Error")
                            }
                        }
                    }
            } else {
                state = state.copy(error = ErrorState.NetworkError, isRefreshing = false)
            }
        }
    }


}