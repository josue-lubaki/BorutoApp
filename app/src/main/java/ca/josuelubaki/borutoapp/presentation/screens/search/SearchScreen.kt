package ca.josuelubaki.borutoapp.presentation.screens.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchScreen() {
    Scaffold(
        topBar = { SearchTopBar(
            text = "",
            onTextChange = {},
            onSearchClicked = {},
            onCloseClicked = {}
        ) }
    ){
        Box(
            modifier = Modifier.padding(it)
        ){

        }
    }
}