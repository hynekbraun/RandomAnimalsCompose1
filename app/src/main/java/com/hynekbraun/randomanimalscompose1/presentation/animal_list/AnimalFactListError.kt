package com.hynekbraun.randomanimalscompose1.presentation.animal_list

sealed class AnimalFactListError {
    data class NetworkError(
        val message: String = "Could not load content."
    ) : AnimalFactListError()

    data class NoConnection
        (val message: String = "Please turn on your internet connection.") : AnimalFactListError()

    data class GenericError
        (val message: String = "Something went wrong!!") : AnimalFactListError()


}