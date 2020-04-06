package com.example.moviesapp.di

import com.example.moviesapp.ni.repositories.MainApiRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Darshan Patel
 * Usage: fetching instance of repositories for calling networking api
 * How to call: inject it through coin
 * Useful parameter: will return context and api interface instance to call repository api
 */

val repositoriesInjection = module {
    //for single instance used single keyword
    single { MainApiRepository(androidContext(), mainApiInterceptor = get()) }
}