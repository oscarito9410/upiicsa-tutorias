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
    @Json(name = "ap_paterno") val ap_paterno: String?,
    @Json(name = "ap_materno") val ap_materno: String?,
    @Json(name = "carrera") val carrera: String?,
    @Json(name = "materias_adeudas") val materias_adeudas: Int?,
    @Json(name = "becado") val becado: Boolean?,
    @Json(name = "promedio") val promedio: Float?,
    @Json(name = "estatus") val estatus: String?,
    @Json(name = "turno") val turno: String?
)

data class AddTeacherRequest(
    @Json(name = "boleta") val boleta: String?,
    @Json(name = "id_teacher") val id_teacher: String?
)




