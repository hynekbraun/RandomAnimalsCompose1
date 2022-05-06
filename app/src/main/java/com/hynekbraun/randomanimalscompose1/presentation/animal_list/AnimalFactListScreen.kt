package com.hynekbraun.randomanimalscompose1.presentation.animal_list

import android.content.Context
import android.graphics.Paint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import com.hynekbraun.randomanimalscompose1.R
import com.hynekbraun.randomanimalscompose1.domain.model.AnimalFact
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

@Composable
fun AnimalFactListScreen(
    viewModel: AnimalFactListViewModel = hiltViewModel()
) {
    val state = viewModel.state
    LazyColumn(
        modifier = Modifier.padding(all = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(state.data) { animalFact ->
            AnimalFactListItem(modifier = Modifier, animalFact = animalFact)
        }
    }
}

@Composable
fun AnimalFactListItem(modifier: Modifier, animalFact: AnimalFact) {
    //TODO The loading bar indicator is at the top, need to put it in the middle. Might have to
    //TODO set fixed height, but that looks bad since the picture have all sizes
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(6.dp)
            .fillMaxWidth(0.8f)
            .shadow(10.dp, MaterialTheme.shapes.medium)
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.Center)
                .clip(MaterialTheme.shapes.medium),
            model = animalFact.image_link,
            contentDescription = stringResource(
                R.string.contentDesc_image
            ),
            loading = {
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(200.dp)
                        .align(Alignment.Center),
                )
            },
            error = {
                Image(
                    painter = painterResource(id = R.drawable.ic_error),
                    contentDescription = stringResource(
                        R.string.text_error,
                    )
                )
            },
        )
        Text(
            text = animalFact.name,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter),
            textAlign = TextAlign.Center
        )
    }

}
