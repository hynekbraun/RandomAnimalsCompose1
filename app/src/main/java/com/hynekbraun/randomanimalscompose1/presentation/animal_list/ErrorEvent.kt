package com.hynekbraun.randomanimalscompose1.presentation.animal_list

sealed class ErrorEvent(val message: String){
    data class NetworkErrorEvent(val errorMessage: String = "Network Error"): ErrorEvent(errorMessage)
    data class IOErrorEvent(val errorMessage: String = "IO Error"): ErrorEvent(errorMessage)
    data class HttpErrorEvent(val errorMessage: String = "Http Error"): ErrorEvent(errorMessage)
    data class NoConnectionErrorEvent(val errorMessage: String = "No Connection"): ErrorEvent(errorMessage)
    data class GenericErrorEvent(val errorMessage: String = "Something went wrong"): ErrorEvent(errorMessage)

}
