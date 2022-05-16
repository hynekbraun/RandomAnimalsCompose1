package com.hynekbraun.randomanimalscompose1.presentation.animal_list

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.hynekbraun.randomanimalscompose1.presentation.ErrorState.ErrorState
import com.hynekbraun.randomanimalscompose1.presentation.animal_list.screenState.AnimalFactList
import com.hynekbraun.randomanimalscompose1.presentation.animal_list.screenState.LoadingScreen
import kotlinx.coroutines.launch

@Composable
fun AnimalFactListScreen(
    modifier: Modifier = Modifier,
    viewModel: AnimalFactListViewModel = hiltViewModel(),
    onNavigateToAnimalDetail: (Int) -> Unit
) {
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val state = viewModel.state
    Scaffold(scaffoldState = scaffoldState,
    modifier = Modifier.fillMaxSize()) {
        SwipeRefresh(
            state = swipeRefreshState, onRefresh = {
                viewModel.onEvent(AnimalFactListEvent.Refresh)
            },
            modifier = modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier.fillMaxSize()) {

/*
This works better with observing connection, but I don't think I need it here.
The other option was to show a dialog, but I think It was a bit disturbing
and annoying to have to click on a button
*/
                NoConnectionBanner(
                    state.internetAvailability,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterHorizontally)
                )
                /*
               Probably the worst way to do this, but this is the best I could come up so far.
               I will have to change this and make some research on taht.
                */
                if (state.data.isEmpty() && !state.isLoading) {
                    Log.d("TAG", "AnimalFactListScreen - NoDataScreen")
                    NoDataScreen(
                        data = state.data,
                        modifier = Modifier
                            .fillMaxSize()
                            .align(CenterHorizontally)
                    )
                } else if (state.data.isEmpty() && state.isLoading) {
                    Log.d("TAG", "AnimalFactListScreen - LoadingScreen")
                    LoadingScreen(
                        modifier = Modifier
                            .fillMaxSize()
                    )
                } else if (state.data.isNotEmpty()) {
                    Log.d("TAG", "AnimalFactListScreen - AnimalFactList")
                    AnimalFactList(
                        animalFacts = state.data,
                        onNavigateToAnimalDetail = onNavigateToAnimalDetail,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

        }

    }
}
