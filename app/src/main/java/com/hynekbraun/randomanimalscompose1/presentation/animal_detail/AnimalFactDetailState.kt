package com.hynekbraun.randomanimalscompose1.presentation.animal_detail

import com.hynekbraun.randomanimalscompose1.domain.model.AnimalFact
import com.hynekbraun.randomanimalscompose1.presentation.ErrorState.ErrorState

data class AnimalFactDetailState(
    val data: AnimalFact? = null,
    val isLoading: Boolean = false,
    val error: ErrorState = ErrorState.NoError
)