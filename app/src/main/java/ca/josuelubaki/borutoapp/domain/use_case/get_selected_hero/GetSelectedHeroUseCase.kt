package ca.josuelubaki.borutoapp.domain.use_case.get_selected_hero

import ca.josuelubaki.borutoapp.data.repository.Repository

class GetSelectedHeroUseCase(private val repository: Repository) {
    suspend operator fun invoke(heroId: Int) = repository.getSelectedHero(heroId)
}