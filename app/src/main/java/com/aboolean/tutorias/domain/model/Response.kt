package com.aboolean.tutorias.domain.model

import com.squareup.moshi.Json
import java.io.Serializable


data class TeacherRegistry(
    @Json(name = "id") val id: String?,
    @Json(name = "nombre") val nombre: String?,
    @Json(name = "horas") val horas: Int?,
    @Json(name = "cat_tiempo") val cat_tiempo: String?

)


data class ErrorResponseRegistry(
    @Json(name = "Message") val Message: String?
)

data class Matter(
    @Json(name = "materia") val materia: String?
):Serializable


