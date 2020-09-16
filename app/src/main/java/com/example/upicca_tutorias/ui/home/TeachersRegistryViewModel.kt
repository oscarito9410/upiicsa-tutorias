package com.example.upicca_tutorias.ui.home

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.upicca_tutorias.data.model.AddTeacherRequest
import com.example.upicca_tutorias.domain.model.TeacherRegistry
import com.example.upicca_tutorias.domain.usecase.TeachersRegistryUseCase
import com.example.upicca_tutorias.ui.model.LoginViewState
import com.example.upicca_tutorias.ui.model.TeacherSearchViewState
import com.example.upicca_tutorias.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class TeachersRegistryViewModel(private val teachersRegistryUseCase: TeachersRegistryUseCase) :
    ViewModel() {

    val teacherRegistriesViewState: LiveData<TeacherSearchViewState> get() = _TeacherRegistryState

    private var _TeacherRegistryState = SingleLiveEvent<TeacherSearchViewState>()
    private lateinit var _SharedPreferences: SharedPreferences


    fun getListTeachers(nameTeacher: String) {
        viewModelScope.launch {

            _TeacherRegistryState.postValue(TeacherSearchViewState.OnLoading)
            runCatching {
                teachersRegistryUseCase.getTeachers(nameTeacher)

            }.onSuccess {
                _TeacherRegistryState.postValue(TeacherSearchViewState.OnSuccessTeacherSearch(it))

            }.onFailure {
                _TeacherRegistryState.postValue(TeacherSearchViewState.OnFailedTeacherSearch)
            }

        }
    }

    fun addTeacherRegistry(addTeacherRequest: AddTeacherRequest) {
        viewModelScope.launch {
            _TeacherRegistryState.postValue(TeacherSearchViewState.OnLoading)
            runCatching {
                teachersRegistryUseCase.addTeachers(addTeacherRequest)
            }.onSuccess {
                _TeacherRegistryState.postValue(TeacherSearchViewState.OnSuccessAddTeachersRegistry)
            }.onFailure {
                _TeacherRegistryState.postValue(TeacherSearchViewState.OnFailedTeacherSearch)
            }
        }
    }


    fun getStringPreferences(value: String): String? {
        _SharedPreferences = teachersRegistryUseCase.getSharedPreferences()
        return _SharedPreferences.getString(value, "")

    }


}



