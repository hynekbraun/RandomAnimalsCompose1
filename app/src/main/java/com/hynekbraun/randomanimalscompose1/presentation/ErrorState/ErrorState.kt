package com.hynekbraun.randomanimalscompose1.presentation.ErrorState

sealed class ErrorState() {
    object NetworkError : ErrorState()
    object HttpError : ErrorState()
    object IOError : ErrorState()
    object NoError : ErrorState()
}