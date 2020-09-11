package com.example.upicca_tutorias.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.upicca_tutorias.domain.model.TeacherRegistry

class TeachersRegistryViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private var _teacherRegistries = MutableLiveData<List<TeacherRegistry>>()

    val teacherRegistries: LiveData<List<TeacherRegistry>> get() = _teacherRegistries

    fun fetchTeachersRegistries() {
        //temporal Code
       val list: List<TeacherRegistry> = listOf(TeacherRegistry("Juan Domm","Programacion",""),
           TeacherRegistry("Daniel Mattrs","Matematicas avanzadas",""),
           TeacherRegistry("Luis Daniel Martinez","Filosfia y letras",""))
        _teacherRegistries.postValue(list)
    }
}