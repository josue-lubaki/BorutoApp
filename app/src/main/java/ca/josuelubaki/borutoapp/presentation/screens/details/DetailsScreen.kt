package ca.josuelubaki.borutoapp.presentation.screens.details


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun DetailsScreen(
    navController : NavHostController,
    detailsViewModel : DetailsViewModel = hiltViewModel()
) {
    val selectedHero by detailsViewModel.selectedHero.collectAsState()

    DetailsContent(
        navController = navController,
        selectedHero = selectedHero
    )

}