package com.example.upicca_tutorias.data.remote

import com.example.upicca_tutorias.data.model.AddTeacherRequest
import com.example.upicca_tutorias.data.model.SignInRequest
import com.example.upicca_tutorias.data.model.SignUpRequest
import com.example.upicca_tutorias.domain.model.TeacherRegistry
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserEndpoints {
    @POST("login/validate")
    suspend fun signIn(@Body signInRequest: SignInRequest)

    @POST("login/register")
    suspend fun signUp(@Body signUpRequest: SignUpRequest)

    @GET("search")
    suspend fun getSearchTeacher(@Query("query") nombre_maestro: String):List<TeacherRegistry>

    @POST("teacher/add")
    suspend fun addTeacherRegistry(@Body addTeacherRequest: AddTeacherRequest)

}