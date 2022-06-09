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
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalFactListViewModel
@Inject
constructor(
    private val repository: AnimalFactRepository,
    application: Application
) : AndroidViewModel(application) {
    val _state = mutableStateOf(AnimalFactListState())
    val state by _state
    private val eventChannel = Channel<ErrorEvent>()
    val errorFlow = eventChannel.receiveAsFlow()

    init {
        fetchAnimalFactList()
        Log.d("TAG", "View model tag")
    }

    fun onEvent(event: AnimalFactListEvent) {
        when (event) {
            is AnimalFactListEvent.Refresh -> {
                _state.value = state.copy(
                    isRefreshing = true,
                    isLoading = true
                )
                fetchAnimalFactList(true)
            }
            is AnimalFactListEvent.OnSearchQueryChange -> {
                //create search functionality
            }
        }
    }

    private fun fetchAnimalFactList(fetchFromRemote: Boolean = false) {
        Log.d("TAG", "AnimalFactViewModel - getAnimalFactList trigered")
        viewModelScope.launch {

            repository.getAnimalFacts(fetchFromRemote = fetchFromRemote)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { animalFacts ->
                                _state.value = state.copy(
                                    data = animalFacts
                                )
                            }
/*
This should not be hard coded, but I could not find a way for the swipe button to stay a bit longer
so it feels more natural. Ideally the button should go up when the images load up.
 */
                            delay(1500L)
                            _state.value = state.copy(isRefreshing = false)

                            Log.d("TAG", "AnimalFactViewModel - Success")
                        }
                        is Resource.Loading -> {
                            _state.value = state.copy(
                                isLoading = result.isLoading,
                                isRefreshing = result.isLoading
                            )
                            Log.d("TAG", "AnimalFactViewModel - Loading")

                        }
                        is Resource.Error -> {
                            if (!getApplication<Application>().applicationContext.currentConnectivityState) {
                                eventChannel.send(ErrorEvent.NoConnectionErrorEvent())
                            } else {
                                when (result.errorState) {
                                    is ErrorState.HttpError -> eventChannel.send(ErrorEvent.HttpErrorEvent())
                                    is ErrorState.IOError -> eventChannel.send(ErrorEvent.HttpErrorEvent())
                                    is ErrorState.NetworkError -> eventChannel.send(ErrorEvent.HttpErrorEvent())
                                    is ErrorState.NoError -> {}
                                }
                            }
                            Log.d("TAG", "AnimalFactViewModel - Error")
                        }
                    }
                }
        }
    }
}

