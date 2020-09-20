package com.aboolean.upicca_tutorias.domain.usecase

import android.content.SharedPreferences
import com.aboolean.upicca_tutorias.data.model.AddTeacherRequest
import com.aboolean.upicca_tutorias.data.remote.UserEndpoints

import com.aboolean.upicca_tutorias.domain.model.TeacherRegistry

interface TeachersRegistryUseCase  {
    suspend fun getTeachers(nameTeacher:String): List<TeacherRegistry>
    suspend fun getTutors(boleta:String): List<TeacherRegistry>

    suspend fun addTeachers(addTeacherRequest: AddTeacherRequest)
    fun getSharedPreferences():SharedPreferences

}

class TeachersRegistryUseCaseImpl(private val userEndpoints: UserEndpoints, private val sharedPreferences: SharedPreferences): TeachersRegistryUseCase {


    override suspend fun getTeachers(nameTeacher:String): List<TeacherRegistry> {
       return userEndpoints.getSearchTeacher(nameTeacher)
    }

    override suspend fun addTeachers(addTeacherRequest: AddTeacherRequest) {
        userEndpoints.addTeacherRegistry(addTeacherRequest)
    }

    override fun getSharedPreferences(): SharedPreferences {
       return sharedPreferences
    }

    override suspend fun getTutors(boleta:String): List<TeacherRegistry> {
        return userEndpoints.getTutor(boleta)
    }


}