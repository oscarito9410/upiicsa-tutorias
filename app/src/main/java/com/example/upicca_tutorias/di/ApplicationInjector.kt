package com.example.upicca_tutorias.di

import com.aboolean.movies.data.remote.TutoriasEndpoints
import com.example.upicca_tutorias.domain.usecase.GetTeachersRegistriesUseCase
import com.example.upicca_tutorias.ui.login.LoginViewModel
import com.example.upicca_tutorias.ui.student_registry.RegistryStudentViewModel
import com.example.upicca_tutorias.ui.teacher_registry.TeachersRegistryViewModel
import com.example.upicca_tutorias.utils.Constants.URL_BASE
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


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



val NetworkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(URL_BASE)
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

    single { get<Retrofit>().create(TutoriasEndpoints::class.java) }
}

