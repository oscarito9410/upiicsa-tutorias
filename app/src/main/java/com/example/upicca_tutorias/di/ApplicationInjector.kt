package com.example.upicca_tutorias.di

import com.example.upicca_tutorias.data.remote.UserEndpoints
import com.example.upicca_tutorias.domain.usecase.LoginUseCase
import com.example.upicca_tutorias.domain.usecase.LoginUseCaseImpl
import com.example.upicca_tutorias.ui.signin.login.LoginViewModel
import com.example.upicca_tutorias.ui.signin.signup.RegistryStudentViewModel
import com.example.upicca_tutorias.ui.home.TeachersRegistryViewModel
import com.example.upicca_tutorias.utils.Constants
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val ApplicationModule = module {

    single<LoginUseCase> {
        LoginUseCaseImpl(get() as UserEndpoints)
    }

    viewModel {
        LoginViewModel(get() as LoginUseCase)
    }

    viewModel {
        RegistryStudentViewModel()
    }

    viewModel {
        TeachersRegistryViewModel()
    }
}

val NetworkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    single {
        StethoInterceptor()
    }

    factory {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return@factory loggingInterceptor
    }


    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addNetworkInterceptor(get<StethoInterceptor>())
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    single { get<Retrofit>().create(UserEndpoints::class.java) }
}

