package ca.josuelubaki.borutoapp.presentation.screens.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import ca.josuelubaki.borutoapp.presentation.common.ListContent

@Composable
fun SearchScreen(
    navController : NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    val searchQuery by searchViewModel.searchQuery
    val heroes = searchViewModel.searchHeroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = { SearchTopBar(
            text = searchQuery,
            onTextChange = { searchViewModel.updateSearchQuery(query = it) },
            onSearchClicked = {
                searchViewModel.searchHeroes(query = searchQuery)
            },
            onCloseClicked = {
                navController.popBackStack()
            }
        )},
        content = {
            Box(
                modifier = Modifier.padding(it)
            ) {
                ListContent(
                    heroes = heroes,
                    navController = navController
                )
            }
        }
    )
}