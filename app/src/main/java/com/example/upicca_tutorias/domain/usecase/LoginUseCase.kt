package com.example.upicca_tutorias.domain.usecase

import com.example.upicca_tutorias.data.model.SignInRequest
import com.example.upicca_tutorias.data.remote.UserEndpoints


interface LoginUseCase {
    suspend fun signIn(boleta: String, password: String)
}

class LoginUseCaseImpl(private val userEndpoints: UserEndpoints) : LoginUseCase {

    override suspend fun signIn(boleta: String, password: String) {
        userEndpoints.signIn(SignInRequest(boleta, password))
    }
}