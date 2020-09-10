package com.example.upicca_tutorias.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.upicca_tutorias.domain.usecase.LoginUseCase
import com.example.upicca_tutorias.ui.model.LoginViewState
import com.example.upicca_tutorias.utils.SingleLiveEvent
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