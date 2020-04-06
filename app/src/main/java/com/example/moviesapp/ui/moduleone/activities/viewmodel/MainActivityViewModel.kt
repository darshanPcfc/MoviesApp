package com.example.moviesapp.ui.moduleone.activities.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.moviesapp.base.BaseViewModel
import com.example.moviesapp.ni.remote.response.DiscoverMovieResponse
import com.example.moviesapp.ni.repositories.MainApiRepository
import com.example.moviesapp.ni.retrofit.MainApiInterface

class MainActivityViewModel(application: Application, val mainApiInterface: MainApiInterface) :
    BaseViewModel<IMainActivityNavigator>()  {

    private val mainApiRepository: MainApiRepository

    init {
        mainApiRepository = MainApiRepository(application, mainApiInterface)
    }

    fun callmainApi(sort : String,pageNumber :String): LiveData<DiscoverMovieResponse> {
        return mainApiRepository.moviesData(sort,pageNumber)
    }
}