package ca.josuelubaki.borutoapp.domain.use_case

import ca.josuelubaki.borutoapp.domain.use_case.get_all_heroes.GetAllHeroesUseCase
import ca.josuelubaki.borutoapp.domain.use_case.read_onboarding.ReadOnBoardingUseCase
import ca.josuelubaki.borutoapp.domain.use_case.save_onboarding.SaveOnBoardingUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllHeroesUseCase: GetAllHeroesUseCase
)