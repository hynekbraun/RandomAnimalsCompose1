package com.hynekbraun.randomanimalscompose1.presentation.animal_list

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.hynekbraun.randomanimalscompose1.R
import com.hynekbraun.randomanimalscompose1.domain.model.AnimalFact
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

@Composable
fun AnimalFactListScreen(
    viewModel: AnimalFactListViewModel = hiltViewModel()
) {
    val state = viewModel.state
    LazyColumn(modifier = Modifier.padding(all = 6.dp)) {
        items(state.data) { animalFact ->
            AnimalFactListItem(modifier = Modifier, animalFact = animalFact)
        }
    }
}

@Composable
fun AnimalFactListItem(modifier: Modifier, animalFact: AnimalFact) {
    Card(
        modifier = modifier.padding(6.dp),
    ) {
        AsyncImage(
            model = animalFact.image_link,
            contentDescription = stringResource(R.string.contentDesc_image)
        )
        Text(text = animalFact.name)
    }
}
