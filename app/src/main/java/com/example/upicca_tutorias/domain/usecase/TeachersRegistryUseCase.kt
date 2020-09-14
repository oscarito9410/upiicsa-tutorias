package com.example.upicca_tutorias.domain.usecase

import androidx.lifecycle.LiveData
import com.example.upicca_tutorias.data.remote.UserEndpoints

import com.example.upicca_tutorias.domain.model.TeacherRegistry
import kotlinx.coroutines.withContext

interface TeachersRegistryUseCase  {
    suspend fun getTeachers(nameTeacher:String): List<TeacherRegistry>
}

class TeachersRegistryUseCaseImpl(private val userEndpoints: UserEndpoints): TeachersRegistryUseCase {


    override suspend fun getTeachers(nameTeacher:String): List<TeacherRegistry> {
       return userEndpoints.getSearchTeacher(nameTeacher)
    }





}