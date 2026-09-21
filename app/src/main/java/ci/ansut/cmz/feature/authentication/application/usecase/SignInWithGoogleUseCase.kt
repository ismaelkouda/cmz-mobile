package ci.ansut.cmz.feature.authentication.application.usecase

import ci.ansut.cmz.feature.authentication.domain.model.User
import ci.ansut.cmz.feature.authentication.domain.repository.AuthRepository


class SignInWithGoogleUseCase(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke(
        idToken: String,
    ): User {
        return authRepository.signInWithGoogle(
            idToken = idToken,
        )
    }
}