package com.example.moviesapp.ui.moduleone.fragment.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.moviesapp.base.BaseViewModel
import com.example.moviesapp.ni.remote.response.DiscoverMovieResponse
import com.example.moviesapp.ni.repositories.MainApiRepository
import com.example.moviesapp.ni.retrofit.MainApiInterface

class MovieListViewModel(application: Application, val mainApiInterface: MainApiInterface) :
    BaseViewModel<IMovieListObserver>() {
    private val mainApiRepository: MainApiRepository

    init {
        mainApiRepository = MainApiRepository(application, mainApiInterface)
    }

    fun moviesAPi(sort : String,pageNumber :String): LiveData<DiscoverMovieResponse> {
        return mainApiRepository.moviesData(sort,pageNumber)
    }

    fun filterByPopularity(){
        getNavigator()?.filterByPopularity()
    }

    fun filterByRating(){
        getNavigator()?.filterByRating()
    }
}