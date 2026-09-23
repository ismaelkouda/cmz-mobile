package ci.ansut.cmz.feature.authentication.application.usecase

import ci.ansut.cmz.feature.authentication.domain.repository.AuthRepository

class SignOutUseCase(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke() {
        authRepository.signOut()
    }
}