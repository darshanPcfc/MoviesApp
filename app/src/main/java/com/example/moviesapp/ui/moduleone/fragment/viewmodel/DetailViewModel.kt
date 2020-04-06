package com.example.moviesapp.ui.moduleone.fragment.viewmodel

import android.app.Application
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.base.BaseViewModel
import com.example.moviesapp.ni.repositories.MainApiRepository
import com.example.moviesapp.ni.retrofit.MainApiInterface

class DetailViewModel (application: Application, val mainApiInterface: MainApiInterface) :
    BaseViewModel<IDetailObserver>() {
    private val mainApiRepository: MainApiRepository

    init {
        mainApiRepository = MainApiRepository(application, mainApiInterface)
    }


}