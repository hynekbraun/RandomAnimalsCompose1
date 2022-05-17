package com.hynekbraun.randomanimalscompose1.presentation.animal_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.hynekbraun.randomanimalscompose1.R
import com.hynekbraun.randomanimalscompose1.domain.model.AnimalFact

/*
This composable is used when there is no data to show,
so when the user opens up the app for the first time
with no connection, it does not show him blanc activity
 */

//Might be better to pass just a boolean??
@Composable
fun NoDataScreen(data: List<AnimalFact>, modifier: Modifier = Modifier) {

    if (data.isEmpty()) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_error),
                contentDescription = stringResource(
                    id = R.string.common_error
                )
            )
            Text(text = stringResource(R.string.noDataScreen_noData))
            Text(text = stringResource(R.string.noDataScreen_swipeToRefresh))

        }
    }
}