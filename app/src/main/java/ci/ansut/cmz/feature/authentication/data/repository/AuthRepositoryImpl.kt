package ci.ansut.cmz.feature.authentication.data.repository

import ci.ansut.cmz.feature.authentication.data.mapper.AuthMapper
import ci.ansut.cmz.feature.authentication.data.remote.api.AuthApi
import ci.ansut.cmz.feature.authentication.domain.model.User
import ci.ansut.cmz.feature.authentication.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authApi: AuthApi,
) : AuthRepository {

    override suspend fun signInWithGoogle(
        idToken: String,
    ): User {
        val response = authApi.signInWithGoogle(
            idToken = idToken,
        )

        return AuthMapper.toDomain(response)
    }

    override suspend fun signOut() {
        // Sera implémenté lorsque nous gérerons
        // réellement la session CMZ.
    }
}