package com.hynekbraun.randomanimalscompose1.presentation.animal_list

import com.hynekbraun.randomanimalscompose1.domain.model.AnimalFact
import com.hynekbraun.randomanimalscompose1.presentation.ErrorState.ErrorState

data class AnimalFactListState(
    val data: List<AnimalFact> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: ErrorState = ErrorState.NoError,
    val internetAvailability: Boolean = false
)