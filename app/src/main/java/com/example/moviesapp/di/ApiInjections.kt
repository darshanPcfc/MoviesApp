package com.example.moviesapp.di

import com.example.moviesapp.ni.retrofit.MainApiInterface
import com.example.moviesapp.ni.retrofit.createNetworkClient
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by Darshan Patel 24/02/2020
 * Usage: provide instance to call networking api,koin implementation for dependency
 * How to call: inject it through coin
 * Useful parameter: retrofit interface instance
 */
private val retrofit: Retrofit = createNetworkClient()
private val MAIN_API_INTERCEPTOR: MainApiInterface = retrofit.create(MainApiInterface::class.java)
val apiInjections = module {
    single { MAIN_API_INTERCEPTOR }
}