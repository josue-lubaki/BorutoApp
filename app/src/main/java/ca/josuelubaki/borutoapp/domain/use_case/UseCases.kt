package ca.josuelubaki.borutoapp.domain.use_case

import ca.josuelubaki.borutoapp.domain.use_case.read_onboarding.ReadOnBoardingUseCase
import ca.josuelubaki.borutoapp.domain.use_case.save_onboarding.SaveOnBoardingUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase
)