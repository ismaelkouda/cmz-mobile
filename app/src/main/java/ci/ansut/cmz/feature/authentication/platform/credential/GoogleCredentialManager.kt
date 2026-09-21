package ci.ansut.cmz.feature.authentication.platform.credential

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.NoCredentialException
import ci.ansut.cmz.feature.authentication.data.remote.dto.GoogleCredential
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential

class GoogleCredentialManager(
    private val credentialManager: CredentialManager,
    private val serverClientId: String,
) {

    suspend fun signIn(
        context: Context,
    ): GoogleCredential {
        return try {
            getCredential(
                context = context,
                filterByAuthorizedAccounts = true,
            )
        } catch (_: NoCredentialException) {
            getCredential(
                context = context,
                filterByAuthorizedAccounts = false,
            )
        }
    }

    private suspend fun getCredential(
        context: Context,
        filterByAuthorizedAccounts: Boolean,
    ): GoogleCredential {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(serverClientId)
            .setFilterByAuthorizedAccounts(filterByAuthorizedAccounts)
            .setAutoSelectEnabled(filterByAuthorizedAccounts)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        val result = credentialManager.getCredential(
            context = context,
            request = request,
        )

        val credential = result.credential

        if (
            credential !is CustomCredential ||
            credential.type != GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
        ) {
            throw IllegalStateException(
                "Unsupported credential type: ${credential.type}",
            )
        }

        val googleCredential =
            GoogleIdTokenCredential.createFrom(credential.data)

        return GoogleCredential(
            idToken = googleCredential.idToken,
            email = googleCredential.id,
            displayName = googleCredential.displayName,
            profilePictureUrl = googleCredential.profilePictureUri?.toString(),
        )
    }
}