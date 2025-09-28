
package com.amaze.filemanager.test

import android.content.Context
import android.util.Base64
import com.amaze.filemanager.utils.PasswordUtil
import org.robolectric.annotation.Implementation
import org.robolectric.annotation.Implements
import java.io.IOException
import java.security.GeneralSecurityException
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

@Implements(PasswordUtil::class)
class ShadowPasswordUtil {
    companion object {
        val INSTANCE = ShadowPasswordUtil()
        private const val ALGO_AES = "AES/GCM/NoPadding"
        private const val IV = "LxbHiJhhUXcj" // 12 byte long IV supported by android for GCM
    }

    private var secretKey: SecretKey

    /** Method handles encryption of plain text on various APIs  */
    @Implementation
    @Throws(GeneralSecurityException::class, IOException::class)
    fun encryptPassword(
        context: Context?,
        plainText: String,
        base64Options: Int = Base64.URL_SAFE,
    ): String {
        return aesEncryptPassword(plainText, base64Options)
    }

    /** Method handles decryption of cipher text on various APIs  */
    @Implementation
    @Throws(GeneralSecurityException::class, IOException::class)
    fun decryptPassword(
        context: Context?,
        cipherText: String,
        base64Options: Int = Base64.URL_SAFE,
    ): String {
        return aesDecryptPassword(cipherText, base64Options)
    }

    /** Helper method to encrypt plain text password  */
    @Throws(GeneralSecurityException::class)
    private fun aesEncryptPassword(
        plainTextPassword: String,
        base64Options: Int,
    ): String {
        val cipher = Cipher.getInstance(ALGO_AES)
        val gcmParameterSpec = GCMParameterSpec(128, IV.toByteArray())
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec)
        val encodedBytes = cipher.doFinal(plainTextPassword.toByteArray())
        return Base64.encodeToString(encodedBytes, base64Options)
    }

    /** Helper method to decrypt cipher text password  */
    @Throws(GeneralSecurityException::class)
    private fun aesDecryptPassword(
        cipherPassword: String,
        base64Options: Int,
    ): String {
        val cipher = Cipher.getInstance(ALGO_AES)
        val gcmParameterSpec = GCMParameterSpec(128, IV.toByteArray())
        cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec)
        val decryptedBytes = cipher.doFinal(Base64.decode(cipherPassword, base64Options))
        return String(decryptedBytes)
    }

    init {
        val keyGen = KeyGenerator.getInstance("AES")
        keyGen.init(128)
        secretKey = keyGen.generateKey()
    }
}
