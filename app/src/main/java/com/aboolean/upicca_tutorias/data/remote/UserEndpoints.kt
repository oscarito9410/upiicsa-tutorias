package com.aboolean.upicca_tutorias.data.remote

import com.aboolean.upicca_tutorias.data.model.AddTeacherRequest
import com.aboolean.upicca_tutorias.data.model.SignInRequest
import com.aboolean.upicca_tutorias.data.model.SignUpRequest
import com.aboolean.upicca_tutorias.domain.model.TeacherRegistry
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

    @POST("add")
    suspend fun addTeacherRegistry(@Body addTeacherRequest: AddTeacherRequest)

    @GET("tutor")
    suspend fun getTutor(@Query("boleta") boleta: String):List<TeacherRegistry>

}