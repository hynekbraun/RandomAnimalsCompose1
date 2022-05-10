package com.hynekbraun.randomanimalscompose1.presentation.animal_detail

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.hynekbraun.randomanimalscompose1.R
import com.hynekbraun.randomanimalscompose1.domain.model.AnimalFact
import com.hynekbraun.randomanimalscompose1.domain.model.length
import com.hynekbraun.randomanimalscompose1.domain.model.weight
import com.hynekbraun.randomanimalscompose1.presentation.animal_list.AnimalFactListViewModel

@Composable
fun AnimalFactDetailScreen(
    viewModel: AnimalFactDetailViewModel = hiltViewModel(),
    id: String?
) {
    viewModel.getAnimalFact(id?.toInt() ?: 0)
    val state = viewModel.state
    val scrollState = rememberScrollState()
    state.data?.let { animalFact ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .verticalScroll(scrollState)
        ) {
            Text(
                text = animalFact.name,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(bottom = 6.dp),
                style = MaterialTheme.typography.h1
            )
            Text(
                text = animalFact.latin_name,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(12.dp),
                style = MaterialTheme.typography.h2

            )
            LoadImage(
                animalFact.image_link,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .clip(MaterialTheme.shapes.large)
                    .padding(4.dp)
            )
            ShowData(animalFact, modifier = Modifier.padding(bottom = 6.dp))
        }
    }
}


@Composable
fun LoadImage(image: String, modifier: Modifier) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = image,
        contentDescription = stringResource(
            R.string.contentDesc_image
        ),
        loading = {
            Box(
                Modifier
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
                modifier = Modifier.fillMaxSize()
                    .height(200.dp)
                    .background(color = Color.LightGray),
                painter = painterResource(id = R.drawable.ic_error),
                contentDescription = stringResource(
                    R.string.common_error,
                )
            )
        }
    )
}

@Composable
fun ShowData(data: AnimalFact, modifier: Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(modifier = modifier) {
            Text(
                text = stringResource(R.string.animDetail_type)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = data.animal_type)
        }
        Row(modifier = modifier) {
            Text(text = stringResource(R.string.animDetail_diet))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = data.diet)
        }
        Row(modifier = modifier) {
            Text(text = stringResource(R.string.animDetail_habitat))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = data.geo_range)
        }
        Row(modifier = modifier) {
            Text(text = stringResource(R.string.animDetail_lifespan))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = data.lifespan)
        }
        Row(modifier = modifier) {
            Text(text = stringResource(R.string.animDetail_weight))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = data.weight())
        }
        Row(modifier = modifier) {
            Text(text = stringResource(R.string.animDetail_diet))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = data.length())
        }
    }
}
