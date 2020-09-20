package com.aboolean.tutorias.domain.usecase

import com.aboolean.tutorias.data.model.SignInRequest
import com.aboolean.tutorias.data.remote.UserEndpoints


interface LoginUseCase {
    suspend fun signIn(boleta: String, password: String)
}

class LoginUseCaseImpl(private val userEndpoints: UserEndpoints) : LoginUseCase {

    override suspend fun signIn(boleta: String, password: String) {
        userEndpoints.signIn(SignInRequest(boleta, password))
    }
}