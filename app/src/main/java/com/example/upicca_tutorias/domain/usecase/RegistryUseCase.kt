package com.example.upicca_tutorias.domain.usecase

import android.content.SharedPreferences
import com.example.upicca_tutorias.data.model.SignInRequest
import com.example.upicca_tutorias.data.model.SignUpRequest
import com.example.upicca_tutorias.data.remote.UserEndpoints
import java.util.*


interface RegistryUseCase {
    suspend fun signUp(signUpRequest: SignUpRequest)
    fun getSharedPreferences():SharedPreferences
}

class RegistryUseCaseImpl(private val userEndpoints: UserEndpoints, private val sharedPreferences: SharedPreferences) : RegistryUseCase {

    override suspend fun signUp(signUpRequest: SignUpRequest) {
        userEndpoints.signUp(signUpRequest)
    }

    override fun getSharedPreferences(): SharedPreferences {
       return sharedPreferences
    }


}