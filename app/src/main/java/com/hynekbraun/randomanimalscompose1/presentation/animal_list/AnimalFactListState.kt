package com.hynekbraun.randomanimalscompose1.presentation.animal_list

import com.hynekbraun.randomanimalscompose1.domain.model.AnimalFact

data class AnimalFactListState(
    val data: List<AnimalFact> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null
)