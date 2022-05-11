package com.hynekbraun.randomanimalscompose1.presentation.ErrorState

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ShowErrorMessage(error: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Text(text = error)
    }
}

@Composable
fun LoadingState(modifier: Modifier, error: String?) {
    //FIXME this string cant be hardcoded
    val text = remember { mutableStateOf(error ?: "Please check your connection") }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text.value)
    }

}
