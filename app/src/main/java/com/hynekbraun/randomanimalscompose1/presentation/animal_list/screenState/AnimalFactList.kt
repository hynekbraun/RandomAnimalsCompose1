package com.hynekbraun.randomanimalscompose1.presentation.animal_list.screenState

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.hynekbraun.randomanimalscompose1.R
import com.hynekbraun.randomanimalscompose1.domain.model.AnimalFact
import com.hynekbraun.randomanimalscompose1.presentation.navigation.NavScreen

@Composable
fun AnimalFactList(
    animalFacts: List<AnimalFact>,
    onNavigateToAnimalDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
//    NoDataScreen(animalFacts)
    LazyColumn(
        modifier = modifier
            .padding(all = 6.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(animalFacts) { animalFact ->
            AnimalFactListItem(
                modifier = Modifier,
                animalFact = animalFact,
                onItemClick = onNavigateToAnimalDetail
            )
        }
    }
}

@Composable
fun AnimalFactListItem(
    modifier: Modifier,
    animalFact: AnimalFact,
    onItemClick: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .padding(6.dp)
            .fillMaxWidth(0.9f)
            .height(200.dp)
            .clickable { onItemClick (animalFact.id) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Gray,
                        Color.LightGray
                    )
                )
            )
        ) {
            Text(
                text = animalFact.name,
                modifier = Modifier
            )
            SubcomposeAsyncImage(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(MaterialTheme.shapes.medium),
                model = animalFact.image_link,
                contentDescription = stringResource(
                    R.string.contentDesc_image
                ),
                loading = {
                    Box(
                        modifier
                            .fillMaxSize()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                },
                error = {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.ic_error),
                        contentDescription = stringResource(
                            R.string.common_error,
                        )
                    )
                },
            )
        }
    }
}