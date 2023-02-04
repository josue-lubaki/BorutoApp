package ca.josuelubaki.borutoapp.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import ca.josuelubaki.borutoapp.domain.model.Hero
import ca.josuelubaki.borutoapp.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases : UseCases
) : ViewModel() {
    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchHeroes = MutableStateFlow<PagingData<Hero>>(PagingData.empty())
    val searchHeroes = _searchHeroes

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchHeroes(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.searchHeroesUseCase(searchQuery = query).cachedIn(viewModelScope).collect {
                _searchHeroes.value = it
            }
        }
    }
}