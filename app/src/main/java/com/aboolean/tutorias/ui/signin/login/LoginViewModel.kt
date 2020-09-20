package com.aboolean.tutorias.ui.signin.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aboolean.tutorias.domain.usecase.LoginUseCase
import com.aboolean.tutorias.ui.model.LoginViewState
import com.aboolean.tutorias.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private var _LoginViewState = SingleLiveEvent<LoginViewState>()

    val loginViewState: LiveData<LoginViewState>
        get() = _LoginViewState

    fun signIn(boleta: String, password: String) {
        viewModelScope.launch {
            _LoginViewState.postValue(LoginViewState.OnLoading)
            runCatching {
                loginUseCase.signIn(boleta, password)
            }.onSuccess {
                _LoginViewState.postValue(LoginViewState.OnSuccessSignIn)
            }.onFailure {
                _LoginViewState.postValue(LoginViewState.OnFailedSignIn)
            }
        }
    }
}