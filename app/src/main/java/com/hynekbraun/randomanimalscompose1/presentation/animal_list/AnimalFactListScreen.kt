package com.hynekbraun.randomanimalscompose1.presentation.animal_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.hynekbraun.randomanimalscompose1.R
import com.hynekbraun.randomanimalscompose1.domain.model.AnimalFact
import com.hynekbraun.randomanimalscompose1.presentation.ErrorState.LoadingState
import com.hynekbraun.randomanimalscompose1.presentation.ErrorState.ShowErrorMessage
import com.hynekbraun.randomanimalscompose1.presentation.navigation.NavScreen

@Composable
fun AnimalFactListScreen(
    viewModel: AnimalFactListViewModel = hiltViewModel(),
    navController: NavController
) {
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )
    val state = viewModel.state
    SwipeRefresh(state = swipeRefreshState, onRefresh = {
        viewModel.onEvent(AnimalFactListEvent.Refresh)
    }) {
        if (state.isLoading && state.data.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize()) {
                LoadingState(modifier = Modifier.align(Center), error = state.error)
            }
        } else if (!state.error.isNullOrEmpty()) {
            ShowErrorMessage(error = state.error.toString())
        }
        LazyColumn(
            modifier = Modifier
                .padding(all = 6.dp)
                .fillMaxWidth(),
            horizontalAlignment = CenterHorizontally
        ) {
            items(state.data) { animalFact ->
                AnimalFactListItem(
                    modifier = Modifier,
                    animalFact = animalFact,
                    navController = navController
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AnimalFactListItem(
    modifier: Modifier,
    animalFact: AnimalFact,
    navController: NavController
) {
    Card(
        modifier = modifier
            .padding(6.dp)
            .fillMaxWidth(0.9f)
            .height(200.dp),
        onClick = {
            navController.navigate(NavScreen.DetailScreen.withArgs(animalFact.id.toString()))
        }
    ) {
        Column(
            horizontalAlignment = CenterHorizontally,
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
                                .align(Center)
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
