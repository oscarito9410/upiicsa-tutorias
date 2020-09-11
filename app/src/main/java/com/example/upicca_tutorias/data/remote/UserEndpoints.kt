package com.example.upicca_tutorias.data.remote

import com.example.upicca_tutorias.data.model.SignInRequest
import com.example.upicca_tutorias.data.model.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface UserEndpoints {
    @POST("login/validate")
    suspend fun signIn(@Body signInRequest: SignInRequest)

    @POST("login/register")
    suspend fun signUp(@Body signUpRequest: SignUpRequest)
}