package com.example.upicca_tutorias.data.model

import com.squareup.moshi.Json

data class SignInRequest(
    @Json(name = "boleta") val boleta: String?,
    @Json(name = "password") val password: String?
)

data class SignUpRequest(
    @Json(name = "boleta") val boleta: String?,
    @Json(name = "nombre") val nombre: String?,
    @Json(name = "password") val password: String?,
    @Json(name = "ap_paterno") val paterno: String?,
    @Json(name = "ap_materno") val materno: String?,
    @Json(name = "id_carrera") val idCarrera: Int?
)