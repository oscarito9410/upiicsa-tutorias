package com.aboolean.tutorias.ui.home.teacher_registry

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aboolean.tutorias.data.model.AddTeacherRequest
import com.aboolean.tutorias.domain.usecase.TeachersRegistryUseCase
import com.aboolean.tutorias.ui.model.TeacherSearchViewState
import com.aboolean.tutorias.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class TeachersRegistryViewModel(private val teachersRegistryUseCase: TeachersRegistryUseCase) :
    ViewModel() {

    val teacherRegistriesViewState: LiveData<TeacherSearchViewState> get() = _TeacherRegistryState

    private var _TeacherRegistryState = SingleLiveEvent<TeacherSearchViewState>()
    private lateinit var _SharedPreferences: SharedPreferences


    fun getListTutors(boleta: String) {
        viewModelScope.launch {

            _TeacherRegistryState.postValue(TeacherSearchViewState.OnLoading)
            runCatching {
                teachersRegistryUseCase.getTutors(boleta)

            }.onSuccess {
                _TeacherRegistryState.postValue(TeacherSearchViewState.OnSuccessTeacherSearch(it))

            }.onFailure {
                _TeacherRegistryState.postValue(TeacherSearchViewState.OnFailedTeacherSearch)
            }

        }
    }


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



