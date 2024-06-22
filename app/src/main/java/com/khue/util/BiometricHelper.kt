package com.khue.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

/*
 * BiometricHelper is a utility object that simplifies the implementation of biometric authentication
 * functionalities in Android apps. It provides methods to check biometric availability, register user
 * biometrics, and authenticate users using biometric authentication.
 *
 * This object encapsulates the logic for interacting with the BiometricPrompt API and integrates
 * seamlessly with the CryptoManager to encrypt and decrypt sensitive data for secure storage.
 */
object BiometricHelper {

    // Check if biometric authentication is available on the device
    fun isBiometricAvailable(context: Context): Boolean {
        val biometricManager = BiometricManager.from(context)
        return when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.BIOMETRIC_WEAK)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                Toast.makeText(
                    context, "App can authenticate using biometrics.", Toast.LENGTH_SHORT
                ).show()
                true
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                Toast.makeText(
                    context,
                    "No biometrics features available on this device.",
                    Toast.LENGTH_SHORT
                ).show()
                false
            }

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                Toast.makeText(
                    context,
                    "Biometrics features are currently unavailable.",
                    Toast.LENGTH_SHORT
                ).show()
                false
            }

            else -> false
        }
    }

    // Retrieve a BiometricPrompt instance with a predefined callback
    fun getBiometricPrompt(
        context: FragmentActivity,
        onAuthSucceed: (BiometricPrompt.AuthenticationResult) -> Unit
    ): BiometricPrompt {
        val biometricPrompt =
            BiometricPrompt(
                context,
                ContextCompat.getMainExecutor(context),
                object : BiometricPrompt.AuthenticationCallback() {
                    // Handle successful authentication
                    override fun onAuthenticationSucceeded(
                        result: BiometricPrompt.AuthenticationResult
                    ) {
                        Log.e("TAG", "Authentication Succeeded: ${result.cryptoObject}")
                        // Execute custom action on successful authentication
                        onAuthSucceed(result)
                    }

                    // Handle authentication errors
                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        Log.e("TAG", "onAuthenticationError")
                        // TODO: Implement error handling
                    }

                    // Handle authentication failures
                    override fun onAuthenticationFailed() {
                        Log.e("TAG", "onAuthenticationFailed")
                        // TODO: Implement failure handling
                    }
                }
            )
        return biometricPrompt
    }

    // Create BiometricPrompt.PromptInfo with customized display text
    fun getPromptInfo(context: FragmentActivity): BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Password for my app")
            .setSubtitle("Check password using your biometric credentials")
            .setNegativeButtonText("Cancel")
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.BIOMETRIC_WEAK)
            .build()
    }
}