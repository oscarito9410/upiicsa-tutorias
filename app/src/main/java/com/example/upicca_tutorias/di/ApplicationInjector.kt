package com.example.upicca_tutorias.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.upicca_tutorias.R
import com.example.upicca_tutorias.data.remote.UserEndpoints
import com.example.upicca_tutorias.domain.usecase.*
import com.example.upicca_tutorias.ui.SplashActivity
import com.example.upicca_tutorias.ui.signin.login.LoginViewModel
import com.example.upicca_tutorias.ui.signin.signup.RegistryStudentViewModel
import com.example.upicca_tutorias.ui.home.TeachersRegistryViewModel
import com.example.upicca_tutorias.utils.Constants
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
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

    single<RegistryUseCase> {
        RegistryUseCaseImpl(get() as UserEndpoints, get() as SharedPreferences)
    }

    viewModel {
        RegistryStudentViewModel(get() as RegistryUseCase)
    }

    single<TeachersRegistryUseCase> {
        TeachersRegistryUseCaseImpl(get() as UserEndpoints)
    }

    //single { get<SplashActivity>() }

    viewModel {
        TeachersRegistryViewModel(get() as TeachersRegistryUseCase)
    }

}


val NetworkModule = module {


    single {
        androidContext().getSharedPreferences(
            androidContext().getString(R.string.prefs_name_tutorias),
            Context.MODE_PRIVATE
        )
    }

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

