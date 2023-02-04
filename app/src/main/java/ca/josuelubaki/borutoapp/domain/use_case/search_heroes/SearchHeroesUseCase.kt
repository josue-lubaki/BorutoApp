package ca.josuelubaki.borutoapp.domain.use_case.search_heroes

import androidx.paging.PagingData
import ca.josuelubaki.borutoapp.data.repository.Repository
import ca.josuelubaki.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke(searchQuery: String) : Flow<PagingData<Hero>> {
        return repository.searchHeroes(searchQuery)
    }
}