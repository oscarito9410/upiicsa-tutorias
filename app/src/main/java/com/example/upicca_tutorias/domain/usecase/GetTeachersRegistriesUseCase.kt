package com.example.upicca_tutorias.domain.usecase

import androidx.lifecycle.LiveData

import com.example.upicca_tutorias.domain.model.TeacherRegistry

interface GetTeachersRegistriesUseCase  {
    fun getTeachers(): LiveData<List<TeacherRegistry>>
}

class GetTeachersRegistriesUseCaseImpl: GetTeachersRegistriesUseCase {
    override fun getTeachers(): LiveData<List<TeacherRegistry>> {

        TODO("Not yet implemented")
    }


}