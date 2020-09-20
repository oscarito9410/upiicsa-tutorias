package com.aboolean.tutorias.ui.signin.signup

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aboolean.tutorias.data.model.SignUpRequest
import com.aboolean.tutorias.domain.model.ErrorResponseRegistry
import com.aboolean.tutorias.domain.usecase.RegistryUseCase
import com.aboolean.tutorias.ui.model.RegistryViewState
import com.aboolean.tutorias.utils.SingleLiveEvent
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegistryStudentViewModel(private val registryUseCase: RegistryUseCase) : ViewModel() {


    private var _RegistryViewState = SingleLiveEvent<RegistryViewState>()
    private lateinit var _EditorShared: SharedPreferences.Editor
    var gson = Gson()

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
                val errorResponseRegistry:ErrorResponseRegistry
                if((it as HttpException).response()!=null){
                    errorResponseRegistry = gson.fromJson(it.response()!!.errorBody()!!.string(),ErrorResponseRegistry::class.java)
                    _RegistryViewState.postValue(RegistryViewState.OnFailedSignUp(errorResponseRegistry))
                }

            }
        }
    }

    fun saveStringPreferences(value: String) {
        _EditorShared = registryUseCase.getSharedPreferences().edit()
        _EditorShared.putString("idValue",value);
        _EditorShared.apply()

    }


}