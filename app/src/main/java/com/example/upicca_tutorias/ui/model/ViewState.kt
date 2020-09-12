package com.example.upicca_tutorias.ui.model

/**
 * This sealed class will help us to notify to the view about changes
 * in the favorite movies screen
 */

sealed class LoginViewState {
    object OnSuccessSignIn : LoginViewState()
    object OnFailedSignIn : LoginViewState()
    object OnLoading : LoginViewState()
}

sealed class RegistryViewState {
    object OnSuccessSignUp : RegistryViewState()
    object OnFailedSignUp : RegistryViewState()
    object OnLoading : RegistryViewState()
}