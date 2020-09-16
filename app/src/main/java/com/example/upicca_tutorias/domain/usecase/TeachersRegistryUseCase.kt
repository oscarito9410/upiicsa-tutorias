package com.example.upicca_tutorias.domain.usecase

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.example.upicca_tutorias.data.model.AddTeacherRequest
import com.example.upicca_tutorias.data.remote.UserEndpoints

import com.example.upicca_tutorias.domain.model.TeacherRegistry
import kotlinx.coroutines.withContext

interface TeachersRegistryUseCase  {
    suspend fun getTeachers(nameTeacher:String): List<TeacherRegistry>
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


}