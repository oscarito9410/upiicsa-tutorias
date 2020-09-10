package com.example.upicca_tutorias.di

import com.example.upicca_tutorias.ui.login.LoginViewModel
import com.example.upicca_tutorias.ui.signup.RegistryStudentViewModel
import com.example.upicca_tutorias.ui.home.TeachersRegistryViewModel
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


