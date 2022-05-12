package com.hynekbraun.randomanimalscompose1.util

import com.hynekbraun.randomanimalscompose1.presentation.ErrorState.ErrorState

sealed class Resource<T>(val data: T? = null, val error: ErrorState = ErrorState.NoError) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(error: ErrorState, data: T? = null) : Resource<T>(data = data, error = error)
    class Loading<T>(val isLoading: Boolean = true) : Resource<T>(null)
}