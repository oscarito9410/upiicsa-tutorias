package com.example.upicca_tutorias.di

import com.example.upicca_tutorias.domain.usecase.GetTeachersRegistriesUseCase
import com.example.upicca_tutorias.ui.login.LoginViewModel
import com.example.upicca_tutorias.ui.student_registry.RegistryStudentViewModel
import com.example.upicca_tutorias.ui.teacher_registry.TeachersRegistryViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val ApplicationModule = module {

    viewModel {
        LoginViewModel()
    }

    viewModel {
        RegistryStudentViewModel()
    }

    viewModel {
        TeachersRegistryViewModel()
    }
}


