package ca.josuelubaki.borutoapp.domain.use_case.save_onboarding

import ca.josuelubaki.borutoapp.data.repository.Repository

class SaveOnBoardingUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(completed: Boolean) {
        repository.onSaveBoardingState(completed)
    }
}