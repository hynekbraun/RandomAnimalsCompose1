package com.hynekbraun.randomanimalscompose1.presentation.ErrorState

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.hynekbraun.randomanimalscompose1.R


@Composable
fun ShowErrorMessage(modifier: Modifier, error: ErrorState) {
    var errorText by remember {
        mutableStateOf("")
    }
    when (error) {
        is ErrorState.NetworkError -> errorText = stringResource(id = R.string.error_network)
        is ErrorState.IOError -> errorText = stringResource(id = R.string.error_IO)
        is ErrorState.HttpError -> errorText = stringResource(id = R.string.error_Http)
    }
    Text(
        modifier = modifier,
        text = errorText
    )
}

@Composable
fun LoadingState(modifier: Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_empty),
        contentDescription = stringResource(R.string.error_empty)
    )
    CircularProgressIndicator(
        modifier = modifier
    )

}
