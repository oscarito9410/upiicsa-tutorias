package com.aboolean.upicca_tutorias.domain.usecase

import android.content.SharedPreferences
import com.aboolean.upicca_tutorias.data.model.SignUpRequest
import com.aboolean.upicca_tutorias.data.remote.UserEndpoints


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