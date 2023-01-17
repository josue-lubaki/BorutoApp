package ca.josuelubaki.borutoapp.domain.use_case.get_all_heroes

import androidx.paging.PagingData
import ca.josuelubaki.borutoapp.data.repository.Repository
import ca.josuelubaki.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class GetAllHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke() : Flow<PagingData<Hero>> = repository.getAllHeroes()
}