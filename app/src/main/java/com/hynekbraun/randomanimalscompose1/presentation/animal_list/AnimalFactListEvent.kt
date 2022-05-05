package com.hynekbraun.randomanimalscompose1.presentation.animal_list

sealed class AnimalFactListEvent {
    object Refresh : AnimalFactListEvent()
    data class OnSearchQueryChange(val query: String) : AnimalFactListEvent()
}
