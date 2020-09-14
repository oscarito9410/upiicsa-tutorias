package com.example.upicca_tutorias.ui.model

import com.example.upicca_tutorias.domain.model.TeacherRegistry

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


sealed class TeacherSearchViewState {
    data class OnSuccessTeacherSearch(val list: List<TeacherRegistry>):TeacherSearchViewState()
    object OnFailedTeacherSearch: TeacherSearchViewState()
    object OnLoading : TeacherSearchViewState()
}