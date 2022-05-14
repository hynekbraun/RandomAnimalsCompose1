package com.hynekbraun.randomanimalscompose1.presentation.animal_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hynekbraun.randomanimalscompose1.R

@Composable
fun NoConnectionBanner(hasNetwork: Boolean, modifier: Modifier =  Modifier) {
    if (!hasNetwork) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Text(
                text = stringResource(id = R.string.error_network),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = MaterialTheme.shapes.small)
                    .background(Color.LightGray)
                    .padding(4.dp)
                    .align(Alignment.Center),
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }
    }
}