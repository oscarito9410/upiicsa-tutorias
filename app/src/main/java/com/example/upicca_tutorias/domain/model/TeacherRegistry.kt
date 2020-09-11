package com.example.upicca_tutorias.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeacherRegistry(val fullName: String,
                           val matter: String,
                           val posterPath: String) : Parcelable
