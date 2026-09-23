package ci.ansut.cmz.core.security.crypto

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

class SecureCipher {

    private val keyStore: KeyStore =
        KeyStore.getInstance(ANDROID_KEYSTORE).apply {
            load(null)
        }

    fun encrypt(value: String?): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)

        cipher.init(
            Cipher.ENCRYPT_MODE,
            getOrCreateSecretKey(),
        )

        val encryptedValue = cipher.doFinal(
            value?.toByteArray(Charsets.UTF_8),
        )

        val iv = cipher.iv

        val encryptedIv = Base64.encodeToString(
            iv,
            Base64.NO_WRAP,
        )

        val encryptedData = Base64.encodeToString(
            encryptedValue,
            Base64.NO_WRAP,
        )

        return "$encryptedIv$SEPARATOR$encryptedData"
    }

    fun decrypt(value: String): String {
        val parts = value.split(
            SEPARATOR,
            limit = 2,
        )

        require(parts.size == 2) {
            "Invalid encrypted value."
        }

        val iv = Base64.decode(
            parts[0],
            Base64.NO_WRAP,
        )

        val encryptedData = Base64.decode(
            parts[1],
            Base64.NO_WRAP,
        )

        val cipher = Cipher.getInstance(TRANSFORMATION)

        cipher.init(
            Cipher.DECRYPT_MODE,
            getOrCreateSecretKey(),
            GCMParameterSpec(
                GCM_TAG_LENGTH_BITS,
                iv,
            ),
        )

        return cipher
            .doFinal(encryptedData)
            .toString(Charsets.UTF_8)
    }

    private fun getOrCreateSecretKey(): SecretKey {
        val existingKey = keyStore.getKey(
            KEY_ALIAS,
            null,
        ) as? SecretKey

        if (existingKey != null) {
            return existingKey
        }

        return KeyGenerator
            .getInstance(
                KeyProperties.KEY_ALGORITHM_AES,
                ANDROID_KEYSTORE,
            )
            .apply {
                init(
                    KeyGenParameterSpec
                        .Builder(
                            KEY_ALIAS,
                            KeyProperties.PURPOSE_ENCRYPT or
                                    KeyProperties.PURPOSE_DECRYPT,
                        )
                        .setBlockModes(
                            KeyProperties.BLOCK_MODE_GCM,
                        )
                        .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_NONE,
                        )
                        .setKeySize(KEY_SIZE_BITS)
                        .build(),
                )
            }
            .generateKey()
    }

    private companion object {
        const val ANDROID_KEYSTORE = "AndroidKeyStore"

        const val KEY_ALIAS = "cmz_session_key"

        const val TRANSFORMATION = "AES/GCM/NoPadding"

        const val KEY_SIZE_BITS = 256

        const val GCM_TAG_LENGTH_BITS = 128

        const val SEPARATOR = ":"
    }
}