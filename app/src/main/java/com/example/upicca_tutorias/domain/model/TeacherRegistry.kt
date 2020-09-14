package com.example.upicca_tutorias.domain.model

import com.squareup.moshi.Json


data class TeacherRegistry(
    @Json(name = "id") val id: Int?,
    @Json(name = "nombre") val nombre: String?,
    @Json(name = "horas") val horas: Int?,
    @Json(name = "cat_tiempo") val cat_tiempo: String?
)

