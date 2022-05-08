package com.hynekbraun.randomanimalscompose1.presentation.animal_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.hynekbraun.randomanimalscompose1.presentation.animal_list.AnimalFactListViewModel

@Composable
fun AnimalFactDetailScreen(
    viewModel: AnimalFactDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Text(text = state.data?.name ?: "Null string")
}