package com.example.upicca_tutorias.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.upicca_tutorias.domain.model.TeacherRegistry
import com.example.upicca_tutorias.domain.usecase.TeachersRegistryUseCase
import com.example.upicca_tutorias.ui.model.TeacherSearchViewState
import com.example.upicca_tutorias.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class TeachersRegistryViewModel(private val teachersRegistryUseCase: TeachersRegistryUseCase) :
    ViewModel() {

    val teacherRegistriesViewState: LiveData<TeacherSearchViewState> get() = _TeacherRegistryState

    private var _TeacherRegistryState = SingleLiveEvent<TeacherSearchViewState>()

    lateinit var list: List<TeacherRegistry>


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


}



