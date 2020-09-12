package com.example.upicca_tutorias.ui.signin.signup

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.upicca_tutorias.data.model.SignUpRequest
import com.example.upicca_tutorias.domain.usecase.RegistryUseCase
import com.example.upicca_tutorias.ui.model.LoginViewState
import com.example.upicca_tutorias.ui.model.RegistryViewState
import com.example.upicca_tutorias.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class RegistryStudentViewModel(private val registryUseCase: RegistryUseCase) : ViewModel() {


    private var _RegistryViewState = SingleLiveEvent<RegistryViewState>()
    private lateinit var _EditorShared: SharedPreferences.Editor

    val registryViewState: LiveData<RegistryViewState>
        get() = _RegistryViewState

    fun signUp(singUpRequest: SignUpRequest) {
        viewModelScope.launch {
            _RegistryViewState.postValue(RegistryViewState.OnLoading)
            runCatching {
                registryUseCase.signUp(singUpRequest)
            }.onSuccess {
                _RegistryViewState.postValue(RegistryViewState.OnSuccessSignUp)
            }.onFailure {
                _RegistryViewState.postValue(RegistryViewState.OnFailedSignUp)
            }
        }
    }

    fun saveStringPreferences(value: String) {
        _EditorShared = registryUseCase.getSharedPreferences().edit()
        _EditorShared.putString("idValue",value);
        _EditorShared.apply()

    }


}