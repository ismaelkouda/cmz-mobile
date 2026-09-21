package ci.ansut.cmz.feature.authentication.domain.repository

import ci.ansut.cmz.feature.authentication.domain.model.User


interface AuthRepository {

    suspend fun signInWithGoogle(
        idToken: String,
    ): User

    suspend fun signOut()
}